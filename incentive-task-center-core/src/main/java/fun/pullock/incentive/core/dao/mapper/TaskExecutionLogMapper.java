package fun.pullock.incentive.core.dao.mapper;

import fun.pullock.incentive.core.dao.model.TaskExecutionLogDO;

public interface TaskExecutionLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TaskExecutionLogDO row);

    int insertSelective(TaskExecutionLogDO row);

    TaskExecutionLogDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskExecutionLogDO row);

    int updateByPrimaryKey(TaskExecutionLogDO row);
}