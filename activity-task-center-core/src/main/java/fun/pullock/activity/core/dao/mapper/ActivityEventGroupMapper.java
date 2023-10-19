package fun.pullock.activity.core.dao.mapper;

import fun.pullock.activity.core.dao.model.ActivityEventGroupDO;

public interface ActivityEventGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActivityEventGroupDO row);

    int insertSelective(ActivityEventGroupDO row);

    ActivityEventGroupDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityEventGroupDO row);

    int updateByPrimaryKey(ActivityEventGroupDO row);
}