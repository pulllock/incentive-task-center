package me.cxis.activity.core.strategy.task.rule;

import jakarta.annotation.Resource;
import me.cxis.activity.api.enums.TaskRuleType;
import me.cxis.activity.core.manager.ActivityTaskExecutionLogManager;
import me.cxis.activity.core.manager.ActivityTaskRewardLogManager;
import me.cxis.activity.core.model.ActivityTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 20-每一次
 */
@Component
public class TaskRuleType20Handler implements TaskRuleTypeHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskRuleType20Handler.class);

    @Resource
    private ActivityTaskRewardLogManager activityTaskRewardLogManager;

    @Resource
    private ActivityTaskExecutionLogManager activityTaskExecutionLogManager;

    @Override
    public TaskRuleType getTaskRuleType() {
        return TaskRuleType.TYPE_20;
    }

    @Override
    public void executeTask(Long userId, String source, ActivityTask task) {
        // 每一次，直接发放奖励，记录执行日志和奖励日志

        // 记录执行日志
        boolean needReward = true;
        String sourceId = UUID.randomUUID().toString();
        activityTaskExecutionLogManager.add(userId, task.getId(),needReward, source, sourceId);

        // 需要发放奖励、记录奖励日志
        LOGGER.info("！！！！！！发放奖励！！！！！！");
        boolean rewardResult = true;
        int rewardStatus = rewardResult ? 2 : 1;
        activityTaskRewardLogManager.add(userId, task.getId(), rewardStatus, source, sourceId);
    }
}
