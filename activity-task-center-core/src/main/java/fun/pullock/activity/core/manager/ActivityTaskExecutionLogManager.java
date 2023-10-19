package fun.pullock.activity.core.manager;

import jakarta.annotation.Resource;
import fun.pullock.activity.core.dao.ActivityTaskExecutionLogDao;
import fun.pullock.activity.core.dao.model.ActivityTaskExecutionLogDO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ActivityTaskExecutionLogManager {

    @Resource
    private ActivityTaskExecutionLogDao activityTaskExecutionLogDao;

    public boolean add(Long userId, Long taskId, boolean needReward, String source, String sourceId) {
        ActivityTaskExecutionLogDO log = new ActivityTaskExecutionLogDO();
        log.setCreateTime(LocalDateTime.now());
        log.setUpdateTime(log.getCreateTime());
        log.setCreatorId(0L);
        log.setModifierId(0L);
        log.setDeleted(false);
        log.setVersion(1L);
        log.setUserId(userId);
        log.setTaskId(taskId);
        log.setNeedReward(needReward);
        log.setSource(source);
        log.setSourceId(sourceId);
        return activityTaskExecutionLogDao.insertSelective(log) == 1;
    }
}
