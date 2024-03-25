package fun.pullock.incentive.core.dao.mapper;

import fun.pullock.incentive.core.dao.model.UserTaskDO;

public interface UserTaskMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserTaskDO row);

    int insertSelective(UserTaskDO row);

    UserTaskDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserTaskDO row);

    int updateByPrimaryKey(UserTaskDO row);
}