package fun.pullock.incentive.core.manager;

import jakarta.annotation.Resource;
import fun.pullock.incentive.core.dao.TaskRewardLogDao;
import fun.pullock.incentive.core.dao.model.TaskRewardLogDO;
import fun.pullock.incentive.core.model.TaskRewardLog;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskRewardLogManager {

    @Resource
    private TaskRewardLogDao taskRewardLogDao;


    public List<TaskRewardLog> query(Long userId, Long taskId, LocalDateTime startDate, LocalDateTime endDate) {
        List<TaskRewardLogDO> logDOS = taskRewardLogDao.query(userId, taskId, startDate, endDate);
        if (CollectionUtils.isEmpty(logDOS)) {
            return null;
        }
        return logDOS.stream().map(this::toTaskRewardLog).collect(Collectors.toList());
    }

    public boolean add(Long userId, Long taskId, Integer status, String source, String sourceId) {
        TaskRewardLogDO log = new TaskRewardLogDO();
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
        return taskRewardLogDao.insertSelective(log) == 1;
    }

    public boolean updateStatus(Long id, int status) {
        return taskRewardLogDao.updateStatus(id, status) == 1;
    }

    private TaskRewardLog toTaskRewardLog(TaskRewardLogDO source) {
        if (source == null) {
            return null;
        }

        TaskRewardLog target = new TaskRewardLog();
        // TODO
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
