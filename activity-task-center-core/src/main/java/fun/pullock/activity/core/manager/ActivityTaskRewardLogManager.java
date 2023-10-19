package fun.pullock.activity.core.manager;

import jakarta.annotation.Resource;
import fun.pullock.activity.core.dao.ActivityTaskRewardLogDao;
import fun.pullock.activity.core.dao.model.ActivityTaskRewardLogDO;
import fun.pullock.activity.core.model.ActivityTaskRewardLog;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActivityTaskRewardLogManager {

    @Resource
    private ActivityTaskRewardLogDao activityTaskRewardLogDao;


    public List<ActivityTaskRewardLog> query(Long userId, Long taskId, LocalDateTime startDate, LocalDateTime endDate) {
        List<ActivityTaskRewardLogDO> logDOS = activityTaskRewardLogDao.query(userId, taskId, startDate, endDate);
        if (CollectionUtils.isEmpty(logDOS)) {
            return null;
        }
        return logDOS.stream().map(this::toActivityTaskRewardLog).collect(Collectors.toList());
    }

    public boolean add(Long userId, Long taskId, Integer status, String source, String sourceId) {
        ActivityTaskRewardLogDO log = new ActivityTaskRewardLogDO();
        log.setCreateTime(LocalDateTime.now());
        log.setUpdateTime(log.getCreateTime());
        log.setCreatorId(0L);
        log.setModifierId(0L);
        log.setDeleted(false);
        log.setVersion(1L);
        log.setUserId(userId);
        log.setTaskId(taskId);
        log.setStatus(status);
        log.setSource(source);
        log.setSourceId(sourceId);
        return activityTaskRewardLogDao.insertSelective(log) == 1;
    }

    public boolean updateStatus(Long id, int status) {
        return activityTaskRewardLogDao.updateStatus(id, status) == 1;
    }

    private ActivityTaskRewardLog toActivityTaskRewardLog(ActivityTaskRewardLogDO source) {
        if (source == null) {
            return null;
        }

        ActivityTaskRewardLog target = new ActivityTaskRewardLog();
        // TODO
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
