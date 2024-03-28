package fun.pullock.incentive.core.service;

import com.googlecode.aviator.AviatorEvaluator;
import fun.pullock.general.model.ServiceException;
import fun.pullock.incentive.core.enums.*;
import fun.pullock.incentive.core.model.app.vo.UserTaskVO;
import fun.pullock.incentive.core.model.reqeust.TriggerParam;
import fun.pullock.incentive.core.manager.TaskManager;
import fun.pullock.incentive.core.model.dto.*;
import fun.pullock.incentive.core.strategy.task.complete.after.AfterCompleteHandler;
import fun.pullock.incentive.core.strategy.task.complete.after.AfterCompleteHandlerFactory;
import fun.pullock.incentive.core.strategy.task.complete.engage.CompleteEngageHandler;
import fun.pullock.incentive.core.strategy.task.complete.engage.CompleteEngageHandlerFactory;
import fun.pullock.incentive.core.strategy.task.complete.limit.CompleteLimitContext;
import fun.pullock.incentive.core.strategy.task.complete.limit.CompleteLimitHandler;
import fun.pullock.incentive.core.strategy.task.complete.limit.CompleteLimitHandlerFactory;
import fun.pullock.incentive.core.strategy.task.complete.limit.CompleteLimitResult;
import fun.pullock.starter.redis.lock.RedisLock;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static fun.pullock.incentive.core.enums.CompleteRecordStatus.*;
import static fun.pullock.incentive.core.enums.CompleteType.AUTO;
import static fun.pullock.incentive.core.enums.ErrorCode.*;
import static fun.pullock.incentive.core.enums.EventStatus.DISABLE;
import static fun.pullock.incentive.core.enums.TriggerLogStatus.*;

@Service
public class TaskService {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    @Resource
    private TaskManager taskManager;

    @Resource
    private EventService eventService;

    @Resource
    private TriggerLogService triggerLogService;

    @Resource
    private CompleteLimitHandlerFactory completeLimitHandlerFactory;

    @Resource
    private AfterCompleteHandlerFactory afterCompleteHandlerFactory;

    @Resource
    private CompleteRecordService completeRecordService;

    @Resource
    private RedisLock redisLock;

    @Resource
    private CompleteEngageHandlerFactory completeEngageHandlerFactory;

    public void trigger(TriggerParam param) {
        // 校验参数
        validateTriggerParam(param);

        // 处理触发日志，如果返回值为null表示已经是成功的
        TriggerLogDTO triggerLog = processTriggerLog(param);
        if (triggerLog == null) {
            return;
        }

        // 检查事件，如果返回false表示事件检查未通过
        if (!checkEvent(param, triggerLog)) {
            return;
        }

        // 查询任务，当前实现中一个任务上只能有一个事件
        List<TaskDTO> tasks = taskManager.queryByEvent(param.getEventCode());
        if (CollectionUtils.isEmpty(tasks)) {
            // 更新触发日志
            triggerLogFailed(triggerLog.getId(), TASK_NOT_EXIST);
            return;
        }

        List<TaskCompleteResult> completeResults = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (TaskDTO task : tasks) {
            // 判断任务是否完成
            if (!done(param, task)) {
                continue;
            }

            TaskCompleteResult cr;
            String lockKey = String.format("TriggerTaskLock::%s_%s", param.getUserId(), task.getId());
            redisLock.lock(lockKey, 60 * 1000);
            try {
                // 判断是否达到完成次数限制
                if (reachLimit(param.getUserId(), task, now).getReachLimit()) {
                    continue;
                }

                // 完成后的后续操作
                cr = complete(param, task, now);

            } finally {
                redisLock.unlock(lockKey);
            }

            cr.setTaskId(task.getId());
            completeResults.add(cr);

            // 异步触达
            engage(param, task, cr);
        }

        // 更新触发日志状态
        updateTriggerLog(completeResults, triggerLog);
    }

    public List<UserTaskVO> list(Long userId) {
        List<TaskDTO> tasks = taskManager.queryAllTasks();
        tasks = tasks.stream()
                .sorted(Comparator.comparing(TaskDTO::getSequenceWeight)
                        .thenComparing(TaskDTO::getId)
                )
                .toList();
        LocalDateTime now = LocalDateTime.now();
        return tasks.stream()
                .map(task -> toUserTaskVO(userId, task, now))
                .collect(Collectors.toList());
    }

    public void claim(Long userId, Long id) {
        TaskDTO task = taskManager.queryById(id);
        if (task == null) {
            return;
        }

        if (task.getCompleteType() != CompleteType.MANUAL.getType()) {
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        CompleteLimitResult lr = reachLimit(userId, task, now);

        if (CollectionUtils.isEmpty(lr.getCompleteRecords())) {
            return;
        }

        List<CompleteRecordDTO> candidates = lr.getCompleteRecords()
                .stream()
                .filter(r -> r.getStatus() == TO_BE_CLAIMED.getStatus())
                .toList();

        if (CollectionUtils.isEmpty(candidates)) {
            return;
        }

        for (CompleteRecordDTO candidate : candidates) {
            String lockKey = String.format("TriggerTaskLock::%s_%s", userId, task.getId());
            redisLock.lock(lockKey, 60 * 1000);
            try {
                TaskCompleteResult tcr = afterCompleteHandlerFactory.getHandler(
                        AfterCompleteType.of(task.getAfterCompleteType())
                        )
                        .complete(toTriggerParam(userId, candidate, task), task);
                if (tcr.getCode() != 0) {
                    continue;
                }

                completeRecordService.updateStatus(candidate.getId(), candidate.getStatus(), DONE.getStatus());
            } finally {
                redisLock.unlock(lockKey);
            }
        }
    }

    private TriggerParam toTriggerParam(Long userId, CompleteRecordDTO candidate, TaskDTO task) {
        TriggerParam tp = new TriggerParam();
        tp.setUserId(userId);
        tp.setEventCode(task.getEvents());
        tp.setSource(candidate.getSource());
        tp.setUniqueSourceId(candidate.getUniqueSourceId());
        return tp;
    }

    private UserTaskVO toUserTaskVO(Long userId, TaskDTO taskDTO, LocalDateTime now) {
        UserTaskVO task = new UserTaskVO();
        task.setId(taskDTO.getId());
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());

        CompleteLimitResult limitResult = reachLimit(userId, taskDTO, now);
        task.setLimitNumber(limitResult.getLimitNumber());
        task.setCompletedNumber(limitResult.getCompletedNumber());
        task.setToBeClaimedNumber(limitResult.getToBeClaimedNumber());

        int status = UNDONE.getStatus();
        if (limitResult.getReachLimit()) {
            status = DONE.getStatus();
        }

        if (limitResult.getToBeClaimedNumber() > 0) {
            status = TO_BE_CLAIMED.getStatus();
        }
        task.setStatus(status);

        // 按钮文案
        task.setButtonText(taskDTO.getButtonConfig().getText().get(task.getStatus()));

        // 链接
        if (taskDTO.getRedirectLinkConfig().getUrl() != null) {
            task.setUrl(taskDTO.getRedirectLinkConfig().getUrl().get(task.getStatus()));
        }
        return task;
    }

    private boolean checkEvent(TriggerParam param, TriggerLogDTO triggerLog) {
        // 查询事件
        EventDTO event = eventService.queryByCode(param.getEventCode());
        if (event == null) {
            // 更新触发日志
            triggerLogFailed(triggerLog.getId(), EVENT_NOT_EXIST);
            return false;
        }

        // 校验事件状态
        if (event.getStatus() == DISABLE.getStatus()) {
            // 更新触发日志
            triggerLogFailed(triggerLog.getId(), EVENT_DISABLED);
            return false;
        }

        return true;
    }

    private TriggerLogDTO processTriggerLog(TriggerParam param) {
        // 查询任务触发日志
        TriggerLogDTO triggerLog = triggerLogService.queryByUniqueKey(
                param.getUserId(),
                param.getSource(),
                param.getUniqueSourceId()
        );

        if (triggerLog != null) {
            // 处理中状态
            if (triggerLog.getStatus() == PROCESSING.getStatus()) {
                throw new ServiceException(ErrorCode.CONCURRENCY_ERROR, "重复请求");
            }
            // 失败状态
            else if (triggerLog.getStatus() == FAILED.getStatus()) {
                // 将失败状态改为处理中
                boolean r = triggerLogService.updateStatus(
                        PROCESSING.getStatus(),
                        FAILED.getStatus(),
                        triggerLog.getId()
                );
                // 状态修改失败，可能是并发请求；状态修改成功后可以继续走正常触发任务逻辑
                if (!r) {
                    throw new ServiceException(ErrorCode.CONCURRENCY_ERROR, "重复请求");
                }
                triggerLog.setStatus(PROCESSING.getStatus());
            }
            // 成功状态
            else if (triggerLog.getStatus() == SUCCESS.getStatus()) {
                // 成功
                return null;
            }
        } else {
            // 插入触发日志
            triggerLog = new TriggerLogDTO();
            triggerLog.setCreateTime(LocalDateTime.now());
            triggerLog.setUpdateTime(triggerLog.getCreateTime());
            triggerLog.setUserId(param.getUserId());
            triggerLog.setEventCode(param.getEventCode());
            triggerLog.setStatus(PROCESSING.getStatus());
            triggerLog.setSource(param.getSource());
            triggerLog.setUniqueSourceId(param.getUniqueSourceId());
            triggerLogService.create(triggerLog);
        }

        return triggerLog;
    }

    private void triggerLogFailed(Long id, ErrorCode errorCode) {
        triggerLogService.failed(id, errorCode.getErrorCode(), errorCode.getErrorMsg());
    }

    private void updateTriggerLog(List<TaskCompleteResult> completeResults, TriggerLogDTO triggerLog) {
        int newStatus = SUCCESS.getStatus();
        List<TriggerLogProcessResultDTO> processResults = new ArrayList<>();
        for (TaskCompleteResult completeResult : completeResults) {
            if (completeResult.getCode() != 0) {
                newStatus = FAILED.getStatus();
            }
            TriggerLogProcessResultDTO processResult = new TriggerLogProcessResultDTO();
            processResult.setCode(completeResult.getCode());
            processResult.setMessage(completeResult.getMessage());
            processResults.add(processResult);
        }

        triggerLogService.updateResult(triggerLog.getId(), triggerLog.getStatus(), newStatus, processResults);
    }

    private void engage(TriggerParam param, TaskDTO task, TaskCompleteResult cr) {
        CompleteEngageHandler handler = completeEngageHandlerFactory.getHandler(
                CompleteEngageWay.of(task.getCompleteEngageWay())
        );
        handler.engage(param, task, cr);
    }

    private TaskCompleteResult complete(TriggerParam param, TaskDTO task, LocalDateTime now) {
        TaskCompleteResult result;
        int status;
        // 自动完成
        if (task.getCompleteType() == AUTO.getType()) {
            int afterCompleteType = task.getAfterCompleteType();
            AfterCompleteHandler handler = afterCompleteHandlerFactory.getHandler(
                    AfterCompleteType.of(afterCompleteType)
            );
            result = handler.complete(param, task);
            if (result.getCode() != 0) {
                return result;
            }

            status = DONE.getStatus();
        }
        // 手动完成
        else {
            result = new TaskCompleteResult();
            result.setCode(0);
            result.setMessage("待领取");
            status = TO_BE_CLAIMED.getStatus();
        }

        completeRecordService.create(param, task, result, status, now);
        return result;
    }

    private CompleteLimitResult reachLimit(Long userId, TaskDTO task, LocalDateTime now) {
        CompleteLimitHandler handler = completeLimitHandlerFactory.getHandler(
                CompleteLimitType.of(task.getCompleteLimitRule().getType())
        );
        return handler.reachLimit(new CompleteLimitContext(userId, task, now));
    }

    private boolean done(TriggerParam param, TaskDTO task) {
        String expression = task.getCompleteRule().getExpression();
        Map<String, Object> ruleData = param.getEventRuleData();
        return (Boolean) AviatorEvaluator.getInstance().compile(expression).execute(ruleData);
    }

    private void validateTriggerParam(TriggerParam param) {
        if (param == null) {
            throw new ServiceException(ErrorCode.PARAM_ERROR);
        }

        if (param.getUserId() == null) {
            throw new ServiceException(ErrorCode.PARAM_ERROR, "userId不能为空");
        }

        if (StringUtils.isEmpty(param.getEventCode())) {
            throw new ServiceException(ErrorCode.PARAM_ERROR, "eventCode不能为空");
        }

        if (StringUtils.isAnyEmpty(param.getSource(), param.getUniqueSourceId())) {
            throw new ServiceException(ErrorCode.PARAM_ERROR, "source或uniqueSourceId不能为空");
        }
    }
}
