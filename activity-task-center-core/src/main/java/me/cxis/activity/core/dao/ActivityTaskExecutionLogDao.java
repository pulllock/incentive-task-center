package me.cxis.activity.core.dao;

import jakarta.annotation.Resource;
import me.cxis.activity.core.dao.mapper.ActivityTaskExecutionLogMapper;
import me.cxis.activity.core.dao.model.ActivityTaskExecutionLogDO;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityTaskExecutionLogDao {

    @Resource
    private ActivityTaskExecutionLogMapper activityTaskExecutionLogMapper;

    public int insertSelective(ActivityTaskExecutionLogDO log) {
        return activityTaskExecutionLogMapper.insertSelective(log);
    }
}
