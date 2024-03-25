package fun.pullock.incentive.core.dao;

import jakarta.annotation.Resource;
import fun.pullock.incentive.core.dao.mapper.TaskRewardLogMapper;
import fun.pullock.incentive.core.dao.model.TaskRewardLogDO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TaskRewardLogDao {

    @Resource
    private TaskRewardLogMapper taskRewardLogMapper;

    public List<TaskRewardLogDO> query(Long userId, Long taskId, LocalDateTime startDate, LocalDateTime endDate) {
        return taskRewardLogMapper.query(userId, taskId, startDate, endDate);
    }

    public int insertSelective(TaskRewardLogDO log) {
        return taskRewardLogMapper.insertSelective(log);
    }

    public int updateStatus(Long id, int status) {
        return taskRewardLogMapper.updateStatus(id, status);
    }
}
