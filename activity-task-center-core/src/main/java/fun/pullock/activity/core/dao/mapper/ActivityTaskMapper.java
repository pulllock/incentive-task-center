package fun.pullock.activity.core.dao.mapper;

import fun.pullock.activity.core.dao.model.ActivityTaskDO;

public interface ActivityTaskMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ActivityTaskDO row);

    int insertSelective(ActivityTaskDO row);

    ActivityTaskDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityTaskDO row);

    int updateByPrimaryKey(ActivityTaskDO row);

    ActivityTaskDO selectByEventId(Long eventId);
}