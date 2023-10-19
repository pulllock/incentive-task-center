package fun.pullock.activity.core.dao;

import fun.pullock.activity.core.dao.model.ActivityTaskExecutionLogDO;
import jakarta.annotation.Resource;
import fun.pullock.activity.core.dao.mapper.ActivityTaskExecutionLogMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityTaskExecutionLogDao {

    @Resource
    private ActivityTaskExecutionLogMapper activityTaskExecutionLogMapper;

    public int insertSelective(ActivityTaskExecutionLogDO log) {
        return activityTaskExecutionLogMapper.insertSelective(log);
    }
}
