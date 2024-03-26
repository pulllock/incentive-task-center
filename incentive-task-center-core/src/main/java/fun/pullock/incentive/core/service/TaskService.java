package fun.pullock.incentive.core.service;

import com.googlecode.aviator.AviatorEvaluator;
import fun.pullock.incentive.api.model.TriggerParam;
import fun.pullock.incentive.core.enums.AfterCompleteType;
import fun.pullock.incentive.core.enums.CompleteLimitType;
import fun.pullock.incentive.core.manager.TaskManager;
import fun.pullock.incentive.core.model.dto.*;
import fun.pullock.incentive.core.strategy.task.complete.after.AfterCompleteHandler;
import fun.pullock.incentive.core.strategy.task.complete.after.AfterCompleteHandlerFactory;
import fun.pullock.incentive.core.strategy.task.complete.limit.CompleteLimitHandler;
import fun.pullock.incentive.core.strategy.task.complete.limit.CompleteLimitHandlerFactory;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public void trigger(TriggerParam param) {
        // 校验参数
        validateTriggerParam(param);

        // 校验触发日志
        validateTriggerLog(param);

        // 查询事件
        EventDTO event = eventService.queryByCode(param.getEventCode());
        if (event == null) {
            // TODO 抛异常
            return;
        }

        // 校验事件状态
        if (event.getStatus() == 0) {
            // TODO 更新触发日志
            return;
        }


        // 查询任务，当前实现中一个任务上只能有一个事件
        List<TaskDTO> tasks = taskManager.queryByEvent(param.getEventCode());
        if (CollectionUtils.isEmpty(tasks)) {
            // TODO 更新触发日志
            return;
        }

        List<TaskCompleteResult> completeResults = new ArrayList<>();
        for (TaskDTO task : tasks) {
            // 判断任务是否完成
            if (!done(param, task)) {
                continue;
            }

            // TODO 加锁
            // 判断是否达到完成次数限制
            if (reachLimit(param, task)) {
                continue;
            }

            // 完成后的后续操作
            TaskCompleteResult cr = complete(param, task);
            completeResults.add(cr);
            // TODO 解锁

            // 异步触达
            engage(param, task, cr);

        }

        // TODO 更新触发日志状态
        updateTriggerLog(param, completeResults);
    }

    private void updateTriggerLog(TriggerParam param, List<TaskCompleteResult> completeResults) {

    }

    private void engage(TriggerParam param, TaskDTO task, TaskCompleteResult cr) {
        // TODO 异步触达
    }

    private TaskCompleteResult complete(TriggerParam param, TaskDTO task) {
        TaskCompleteResult result = null;
        int completeRecordStatus = 1;
        // 自动完成
        if (task.getCompleteType() == 2) {
            completeRecordStatus = 1;
            int afterCompleteType = task.getAfterCompleteType();
            AfterCompleteHandler handler = afterCompleteHandlerFactory.getHandler(
                    AfterCompleteType.of(afterCompleteType)
            );
            result = handler.complete(param, task);
        }


        // TODO 插入完成记录表
        return result;
    }

    private boolean reachLimit(TriggerParam triggerParam, TaskDTO task) {
        CompleteLimitHandler handler = completeLimitHandlerFactory.getHandler(
                CompleteLimitType.of(task.getCompleteLimitRule().getType())
        );
        return handler.reachLimit(triggerParam, task);
    }

    private boolean done(TriggerParam param, TaskDTO task) {
        String expression = task.getCompleteRule().getExpression();
        Map<String, Object> ruleData = param.getEventRuleData();
        return (Boolean) AviatorEvaluator.getInstance().compile(expression).execute(ruleData);
    }

    private TriggerLogDTO validateTriggerLog(TriggerParam param) {
        // 查询任务触发日志
        TriggerLogDTO triggerLog = triggerLogService.queryByUniqueKey(
                param.getUserId(),
                param.getSource(),
                param.getUniqueSourceId()
        );
        if (triggerLog != null) {
            // TODO 抛异常
            return null;
        }

        // 插入触发日志
        triggerLog = new TriggerLogDTO();
        boolean r = triggerLogService.create(triggerLog);
        if (!r) {
            // TODO 抛异常
            return null;
        }

        return triggerLog;
    }

    private void validateTriggerParam(TriggerParam param) {
        if (param == null) {
            // TODO 抛异常
            return;
        }

        if (param.getUserId() == null) {
            // TODO 抛异常
            return;
        }

        if (StringUtils.isEmpty(param.getEventCode())) {
            // TODO 抛异常
            return;
        }

        if (StringUtils.isAnyEmpty(param.getSource(), param.getUniqueSourceId())) {
            // TODO 抛异常
            return;
        }
    }
}
