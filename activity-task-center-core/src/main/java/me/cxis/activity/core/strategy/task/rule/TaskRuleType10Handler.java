package me.cxis.activity.core.strategy.task.rule;

import jakarta.annotation.Resource;
import me.cxis.activity.api.enums.TaskRuleType;
import me.cxis.activity.core.manager.ActivityTaskExecutionLogManager;
import me.cxis.activity.core.manager.ActivityTaskRewardLogManager;
import me.cxis.activity.core.model.ActivityTask;
import me.cxis.activity.core.model.ActivityTaskRewardLog;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * 类型：10-一次性
 */
@Component
public class TaskRuleType10Handler implements TaskRuleTypeHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskRuleType10Handler.class);

    @Resource
    private ActivityTaskRewardLogManager activityTaskRewardLogManager;

    @Resource
    private ActivityTaskExecutionLogManager activityTaskExecutionLogManager;

    @Override
    public TaskRuleType getTaskRuleType() {
        return TaskRuleType.TYPE_10;
    }

    @Override
    public void executeTask(Long userId, String source, ActivityTask task) {
        // 查询任务，是否有已完成的任务 状态为 2-已完成
        // 查询条件：userId，taskId
        List<ActivityTaskRewardLog> rewardLogs = activityTaskRewardLogManager.query(userId, task.getId(), null, null);
        if (CollectionUtils.isNotEmpty(rewardLogs)) {
            return;
        }

        // 是否有已完成的
        boolean hasDone = rewardLogs.stream().anyMatch(log -> log.getStatus() > 0);
        // 记录执行日志
        boolean needReward = !hasDone;
        String sourceId = UUID.randomUUID().toString();
        activityTaskExecutionLogManager.add(userId, task.getId(),needReward, source, sourceId);

        if (hasDone) {
            return;
        }

        // 需要发放奖励、记录奖励日志
        LOGGER.info("！！！！！！发放奖励！！！！！！");
        boolean rewardResult = true;
        int rewardStatus = rewardResult ? 2 : 1;
        activityTaskRewardLogManager.add(userId, task.getId(), rewardStatus, source, sourceId);
    }
}
