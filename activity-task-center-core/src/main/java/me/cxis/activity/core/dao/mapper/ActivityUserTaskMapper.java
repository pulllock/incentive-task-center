package me.cxis.activity.core.dao.mapper;

import me.cxis.activity.core.dao.model.ActivityUserTaskDO;

public interface ActivityUserTaskMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActivityUserTaskDO row);

    int insertSelective(ActivityUserTaskDO row);

    ActivityUserTaskDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityUserTaskDO row);

    int updateByPrimaryKey(ActivityUserTaskDO row);
}