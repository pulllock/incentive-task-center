package fun.pullock.incentive.core.job;

import fun.pullock.incentive.core.dynamic.config.DynamicConfig;
import fun.pullock.incentive.core.model.dto.TriggerLogDTO;
import fun.pullock.incentive.core.model.reqeust.TriggerParam;
import fun.pullock.incentive.core.service.TaskService;
import fun.pullock.incentive.core.service.TriggerLogService;
import fun.pullock.starter.redis.lock.RedisLock;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class FailedTaskRetryJob {

    @Resource
    private RedisLock redisLock;

    @Resource
    private TriggerLogService triggerLogService;

    @Resource
    private TaskService taskService;

    @Resource
    private DynamicConfig dynamicConfig;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void failedTaskRetry() {
        String lockKey = "FailedTaskRetryLock";
        redisLock.lock(lockKey, 60 * 10 * 1000);
        try {
            LocalDateTime endTime = LocalDateTime.now().minusDays(dynamicConfig.failedRetryDays)
                    .withHour(0).withMinute(0).withSecond(0).withNano(0);
            Integer batchSize = dynamicConfig.failedBatchSize;

            List<TriggerLogDTO> failedLogs = null;
            Long lastId = null;
            do {
                failedLogs = queryFailedLogs(lastId, endTime, batchSize);
                for (TriggerLogDTO failedLog : failedLogs) {
                    lastId = failedLog.getId();

                    TriggerParam param = new TriggerParam();
                    param.setUserId(failedLog.getUserId());
                    param.setEventCode(failedLog.getEventCode());
                    param.setEventRuleData(failedLog.getEventRuleData());
                    param.setEventTime(failedLog.getEventTime());
                    param.setSource(failedLog.getSource());
                    param.setUniqueSourceId(failedLog.getUniqueSourceId());
                    taskService.trigger(param);
                }
            } while (CollectionUtils.isNotEmpty(failedLogs) && failedLogs.size() >= batchSize);
        } finally {
            redisLock.unlock(lockKey);
        }
    }

    private List<TriggerLogDTO> queryFailedLogs(Long lastId, LocalDateTime endTime, Integer batchSize) {
        return triggerLogService.queryFailedLogs(lastId, endTime, batchSize);
    }
}
