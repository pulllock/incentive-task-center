package fun.pullock.incentive.core.dao.mapper;

import fun.pullock.incentive.core.dao.model.TaskDO;

import java.util.List;

public interface TaskMapper {
    
    int deleteByPrimaryKey(Long id);

    int insert(TaskDO row);

    int insertSelective(TaskDO row);

    TaskDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskDO row);

    int updateByPrimaryKey(TaskDO row);

    List<TaskDO> selectByEvent(String eventCode);
}