package fun.pullock.activity.core.dao.mapper;

import fun.pullock.activity.core.dao.model.ActivityTaskExecutionLogDO;

public interface ActivityTaskExecutionLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ActivityTaskExecutionLogDO row);

    int insertSelective(ActivityTaskExecutionLogDO row);

    ActivityTaskExecutionLogDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityTaskExecutionLogDO row);

    int updateByPrimaryKey(ActivityTaskExecutionLogDO row);
}