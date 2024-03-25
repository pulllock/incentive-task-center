package fun.pullock.incentive.core.strategy.task.rule;

import fun.pullock.incentive.api.enums.TaskRuleType;
import fun.pullock.incentive.core.manager.TaskExecutionLogManager;
import fun.pullock.incentive.core.manager.TaskRewardLogManager;
import fun.pullock.incentive.core.model.Task;
import jakarta.annotation.Resource;
import fun.pullock.incentive.core.model.TaskRewardLog;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 31-周期性（M天N次，完成最后一次才算完成）
 */
@Component
public class TaskRuleType31Handler implements TaskRuleTypeHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskRuleType31Handler.class);

    @Resource
    private TaskRewardLogManager taskRewardLogManager;

    @Resource
    private TaskExecutionLogManager taskExecutionLogManager;

    @Override
    public TaskRuleType getTaskRuleType() {
        return TaskRuleType.TYPE_31;
    }

    @Override
    public void executeTask(Long userId, String source, Task task) {

        // 间隔，5天3次的5
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDate = getStartDate(now, task);

        // 次数，5天3次的次
        Long periodTimes = task.getPeriodTimes();

        // 查询任务，是否有已完成的任务 状态为 2-已完成
        // 查询条件：userId，taskId，当前时间，往前推M秒
        List<TaskRewardLog> rewardLogs = taskRewardLogManager.query(userId, task.getId(), startDate, now);

        if (CollectionUtils.isEmpty(rewardLogs)) {
            // 没有记录
            // 在新的periodInterval之内，需要记录执行日志和奖励日志
            // 记录执行日志
            boolean needReward = true;
            String sourceId = UUID.randomUUID().toString();
            taskExecutionLogManager.add(userId, task.getId(), needReward, source, sourceId);

            // 记录奖励日志，状态为0
            Integer status = 0;
            taskRewardLogManager.add(userId, task.getId(), status, source, sourceId);
        }

        else {
            // 查看是否有已完成的
            List<TaskRewardLog> doneLog = rewardLogs.stream().filter(log -> log.getStatus() > 0).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(doneLog)) {
                // 有已完成的日志
                // 如果有periodTimes条数据，说明还在periodInterval之内，则只记录执行日志
                if (doneLog.size() >= periodTimes) {
                    // 只记录执行日志，不需要记录奖励日志
                    boolean needReward = false;
                    String sourceId = UUID.randomUUID().toString();
                    taskExecutionLogManager.add(userId, task.getId(), needReward, source, sourceId);
                } else {
                    // 在新的periodInterval之内，需要记录执行日志和奖励日志
                    // 记录执行日志
                    boolean needReward = true;
                    String sourceId = UUID.randomUUID().toString();
                    taskExecutionLogManager.add(userId, task.getId(), needReward, source, sourceId);

                    // 记录奖励日志，状态为0
                    Integer status = 0;
                    taskRewardLogManager.add(userId, task.getId(), status, source, sourceId);
                }
            } else {
                // 在新的periodInterval之内，需要记录执行日志和奖励日志
                // 记录执行日志
                boolean needReward = true;
                String sourceId = UUID.randomUUID().toString();
                taskExecutionLogManager.add(userId, task.getId(),needReward, source, sourceId);

                // 记录奖励日志，状态为0
                Integer status = 0;
                taskRewardLogManager.add(userId, task.getId(), status, source, sourceId);
            }
        }

        // 再次检查是否已完成，没有状态为2的，并且有三条状态为0-等待后续更多完成，则说明任务完成，将最后一条任务进行发放奖励并设置状态为已完成
        now = LocalDateTime.now();
        startDate = getStartDate(now, task);

        rewardLogs = taskRewardLogManager.query(userId, task.getId(), startDate, now);
        if (CollectionUtils.isNotEmpty(rewardLogs)) {
            rewardLogs = rewardLogs.stream().filter(log -> log.getStatus().equals(0)).collect(Collectors.toList());
            if (rewardLogs.size() == periodTimes) {
                // 发放奖励
                LOGGER.info("！！！！！！发放奖励！！！！！！");
                // 更新这一批日志状态为发放奖励状态
                for (TaskRewardLog rewardLog : rewardLogs) {
                    taskRewardLogManager.updateStatus(rewardLog.getId(), 2);
                }

            }
        }
    }


    private LocalDateTime getStartDate(LocalDateTime now, Task task) {
        Long periodInterval = task.getPeriodInterval();
        LocalDateTime startDate = now;
        // 相对周期
        if (task.getPeriodType() == 1) {
            // 周期单位：秒
            if (task.getPeriodUnit() == 1) {
                startDate = now.minusSeconds(periodInterval - 1);
            }

            // 周期单位：分
            else if (task.getPeriodUnit() == 2) {
                // 间隔，分 * 60秒
                startDate = now.minusSeconds(periodInterval * 60 - 1);
            }

            // 周期单位：小时
            else if (task.getPeriodUnit() == 3) {
                // 间隔，时 * 60 * 60秒
                startDate = now.minusSeconds(periodInterval * 60 * 60 - 1);
            }

            // 周期单位：天
            else if (task.getPeriodUnit() == 4) {
                startDate = now.toLocalDate().minusDays(periodInterval - 1).atStartOfDay();
            }

            // 周期单位：周
            else if (task.getPeriodUnit() == 5) {
                // 间隔，周*7天
                startDate = now.toLocalDate().minusDays(periodInterval * 7 - 1).atStartOfDay();
            }

            // 周期单位：月
            else if (task.getPeriodUnit() == 6) {
                // 间隔，月 * 30天
                startDate = now.toLocalDate().minusDays(periodInterval * 30 - 1).atStartOfDay();
            }

            // 周期单位：年
            else if (task.getPeriodUnit() == 7) {
                // 间隔，年 * 365天
                startDate = now.toLocalDate().minusDays(periodInterval * 365 - 1).atStartOfDay();
            }
        }
        // 自然周期
        else if (task.getPeriodType() == 2) {
            // 周期单位：秒
            if (task.getPeriodUnit() == 1) {
                startDate = now.minusSeconds(periodInterval - 1);
            }

            // 周期单位：分
            else if (task.getPeriodUnit() == 2) {
                startDate = now.minusMinutes(periodInterval - 1).withSecond(0).withNano(0);
            }

            // 周期单位：小时
            else if (task.getPeriodUnit() == 3) {
                startDate = now.minusHours(periodInterval - 1).withMinute(0).withSecond(0).withNano(0);
            }

            // 周期单位：天
            else if (task.getPeriodUnit() == 4) {
                startDate = now.toLocalDate().minusDays(periodInterval - 1).atStartOfDay();
            }

            // 周期单位：周
            else if (task.getPeriodUnit() == 5) {
                startDate = now.toLocalDate().minusWeeks(periodInterval).atStartOfDay();
                int dayOfWeek = startDate.getDayOfWeek().getValue();
                startDate = startDate.minusDays(dayOfWeek -1);
            }

            // 周期单位：月
            else if (task.getPeriodUnit() == 6) {
                startDate = now.toLocalDate().minusMonths(periodInterval).atStartOfDay();
                int dayOfMonth = startDate.getDayOfMonth();
                startDate = startDate.minusDays(dayOfMonth - 1);
            }

            // 周期单位：年
            else if (task.getPeriodUnit() == 7) {
                int startYear = now.minusYears(periodInterval).getYear();
                startDate = LocalDate.of(startYear, 1, 1).atStartOfDay();
            }
        }

        return startDate;
    }
}
