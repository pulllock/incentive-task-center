package fun.pullock.incentive.core.job;

import fun.pullock.incentive.core.model.dto.TriggerLogDTO;
import fun.pullock.incentive.core.model.reqeust.TriggerParam;
import fun.pullock.incentive.core.service.TaskService;
import fun.pullock.incentive.core.service.TriggerLogService;
import fun.pullock.starter.redis.lock.RedisLock;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FailedTaskRetryJob {

    @Resource
    private RedisLock redisLock;

    @Resource
    private TriggerLogService triggerLogService;

    @Resource
    private TaskService taskService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void failedTaskRetry() {
        String lockKey = "FailedTaskRetryLock";
        redisLock.lock(lockKey, 60 * 10 * 1000);
        try {
            List<TriggerLogDTO> failedLogs = triggerLogService.queryFailedLogs();
            if (CollectionUtils.isEmpty(failedLogs)) {
                return;
            }

            for (TriggerLogDTO failedLog : failedLogs) {
                TriggerParam param = new TriggerParam();
                param.setUserId(failedLog.getUserId());
                param.setEventCode(failedLog.getEventCode());
                param.setEventRuleData(failedLog.getEventRuleData());
                param.setEventTime(failedLog.getEventTime());
                param.setSource(failedLog.getSource());
                param.setUniqueSourceId(failedLog.getUniqueSourceId());
                taskService.trigger(param);
            }
        } finally {
            redisLock.unlock(lockKey);
        }
    }
}
