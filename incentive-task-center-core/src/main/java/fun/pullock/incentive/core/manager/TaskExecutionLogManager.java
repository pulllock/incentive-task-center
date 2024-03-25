package fun.pullock.incentive.core.manager;

import jakarta.annotation.Resource;
import fun.pullock.incentive.core.dao.TaskExecutionLogDao;
import fun.pullock.incentive.core.dao.model.TaskExecutionLogDO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskExecutionLogManager {

    @Resource
    private TaskExecutionLogDao taskExecutionLogDao;

    public boolean add(Long userId, Long taskId, boolean needReward, String source, String sourceId) {
        TaskExecutionLogDO log = new TaskExecutionLogDO();
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
        return taskExecutionLogDao.insertSelective(log) == 1;
    }
}
