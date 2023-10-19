package fun.pullock.activity.core.dao;

import jakarta.annotation.Resource;
import fun.pullock.activity.core.dao.mapper.ActivityTaskRewardLogMapper;
import fun.pullock.activity.core.dao.model.ActivityTaskRewardLogDO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ActivityTaskRewardLogDao {

    @Resource
    private ActivityTaskRewardLogMapper activityTaskRewardLogMapper;

    public List<ActivityTaskRewardLogDO> query(Long userId, Long taskId, LocalDateTime startDate, LocalDateTime endDate) {
        return activityTaskRewardLogMapper.query(userId, taskId, startDate, endDate);
    }

    public int insertSelective(ActivityTaskRewardLogDO log) {
        return activityTaskRewardLogMapper.insertSelective(log);
    }

    public int updateStatus(Long id, int status) {
        return activityTaskRewardLogMapper.updateStatus(id, status);
    }
}
