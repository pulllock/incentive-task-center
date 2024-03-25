package fun.pullock.incentive.core.dao.mapper;

import fun.pullock.incentive.core.dao.model.EventGroupDO;

public interface EventGroupMapper {

    int deleteByPrimaryKey(Long id);

    int insert(EventGroupDO row);

    int insertSelective(EventGroupDO row);

    EventGroupDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EventGroupDO row);

    int updateByPrimaryKey(EventGroupDO row);
}