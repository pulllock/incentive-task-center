package me.cxis.activity.core.service;

import jakarta.annotation.Resource;
import me.cxis.activity.api.model.common.PageQuery;
import me.cxis.activity.api.model.common.PageResult;
import me.cxis.activity.core.manager.*;
import me.cxis.activity.core.model.ActivityEvent;
import me.cxis.activity.core.model.ActivityTask;
import me.cxis.activity.core.model.ActivityTaskRewardLog;
import me.cxis.activity.core.model.ActivityUserTask;
import me.cxis.activity.core.model.app.param.UserTaskPageReq;
import me.cxis.activity.core.model.app.result.UserTaskResp;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ActivityTaskService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ActivityTaskService.class);

    @Resource
    private ActivityTaskManager activityTaskManager;

    @Resource
    private ActivityUserTaskManager activityUserTaskManager;

    @Resource
    private ActivityEventManager activityEventManager;

    @Resource
    private ActivityTaskRewardLogManager activityTaskRewardLogManager;

    @Resource
    private ActivityTaskExecutionLogManager activityTaskExecutionLogManager;

    @Transactional
    public void executeActivityTask(Long userId, String eventCode) {
        String source = "TEST-SOURCE";

        // 查询event，校验event状态
        ActivityEvent activityEvent = activityEventManager.queryByCode(eventCode);


        // 查询event关联的有效的task，配置的时候需要保证每个事件只能有一个任务是有效的
        ActivityTask task = activityTaskManager.queryByEventId(activityEvent.getId());

        // 31-周期性（M天N次，完成最后一次才算完成）
        if (task.getRuleType() == 31) {
            // 5天完成3次，才发放奖励

            // 间隔，5天3次的5
            Long periodInterval = task.getPeriodInterval();
            // 次数，5天3次的次
            Long periodTimes = task.getPeriodTimes();

            // 查询任务，是否有已完成的任务 状态为 2-已完成

            // 查询条件：userId，taskId，当前时间，往前推M天
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime startDate = now.toLocalDate().minusDays(periodInterval - 1).atStartOfDay();

            List<ActivityTaskRewardLog> rewardLogs = activityTaskRewardLogManager.query(userId, task.getId(), startDate, now);

            if (CollectionUtils.isEmpty(rewardLogs)) {
                // 没有记录
                // 在新的periodInterval之内，需要记录执行日志和奖励日志
                // 记录执行日志
                boolean needReward = true;
                String sourceId = UUID.randomUUID().toString();
                activityTaskExecutionLogManager.add(userId, task.getId(),needReward, source, sourceId);

                // 记录奖励日志，状态为0
                Integer status = 0;
                activityTaskRewardLogManager.add(userId, task.getId(), status, source, sourceId);
            }
            else {
                // 查看是否有已完成的
                List<ActivityTaskRewardLog> doneLog = rewardLogs.stream().filter(log -> log.getStatus() > 0).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(doneLog)) {
                    // 有已完成的日志
                    // 如果有periodTimes条数据，说明还在periodInterval之内，则只记录执行日志
                    if (doneLog.size() >= periodTimes) {
                        // 只记录执行日志，不需要记录奖励日志
                        boolean needReward = false;
                        String sourceId = UUID.randomUUID().toString();
                        activityTaskExecutionLogManager.add(userId, task.getId(),needReward, source, sourceId);
                    } else {
                        // 在新的periodInterval之内，需要记录执行日志和奖励日志
                        // 记录执行日志
                        boolean needReward = true;
                        String sourceId = UUID.randomUUID().toString();
                        activityTaskExecutionLogManager.add(userId, task.getId(),needReward, source, sourceId);

                        // 记录奖励日志，状态为0
                        Integer status = 0;
                        activityTaskRewardLogManager.add(userId, task.getId(), status, source, sourceId);
                    }
                } else {
                    // 在新的periodInterval之内，需要记录执行日志和奖励日志
                    // 记录执行日志
                    boolean needReward = true;
                    String sourceId = UUID.randomUUID().toString();
                    activityTaskExecutionLogManager.add(userId, task.getId(),needReward, source, sourceId);

                    // 记录奖励日志，状态为0
                    Integer status = 0;
                    activityTaskRewardLogManager.add(userId, task.getId(), status, source, sourceId);
                }
            }

            // 再次检查是否已完成，没有状态为2的，并且有三条状态为0-等待后续更多完成，则说明任务完成，将最后一条任务进行发放奖励并设置状态为已完成
            now = LocalDateTime.now();
            startDate = now.toLocalDate().minusDays(periodInterval - 1).atStartOfDay();
            rewardLogs = activityTaskRewardLogManager.query(userId, task.getId(), startDate, now);
            if (CollectionUtils.isNotEmpty(rewardLogs)) {
                rewardLogs = rewardLogs.stream().filter(log -> log.getStatus().equals(0)).collect(Collectors.toList());
                if (rewardLogs.size() == periodTimes) {
                    // 发放奖励
                    LOGGER.info("！！！！！！发放奖励！！！！！！");
                    // 更新这一批日志状态为发放奖励状态
                    for (ActivityTaskRewardLog rewardLog : rewardLogs) {
                        activityTaskRewardLogManager.updateStatus(rewardLog.getId(), 2);
                    }

                }
            }

        } else {

            // 如果是可下发的任务，则给用户下发任务

            // 根据规则和日志查询任务是否已经完成

            // 任务已完成，记录执行日志，不需要发放奖励

            // 任务未完成，记录执行日志，记录发放奖励日志，进行奖励发放

        }

    }



    public PageResult<UserTaskResp> pageQueryUserTasks(PageQuery<UserTaskPageReq> req) {
        PageResult<UserTaskResp> result = new PageResult<>();
        int count = activityUserTaskManager.countUserTasks(req);
        result.setTotal(count);

        if (count == 0) {
            return result;
        }

        List<ActivityUserTask> tasks = activityUserTaskManager.pageQueryUserTasks(req);
        if (CollectionUtils.isEmpty(tasks)) {
            return result;
        }

        result.setList(tasks.stream().map(this::toUserTaskResp).collect(Collectors.toList()));
        return result;
    }

    private UserTaskResp toUserTaskResp(ActivityUserTask source) {
        if (source == null) {
            return null;
        }

        UserTaskResp target = new UserTaskResp();
        // TODO
        return target;
    }
}
