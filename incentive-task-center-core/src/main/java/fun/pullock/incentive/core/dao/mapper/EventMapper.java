package fun.pullock.incentive.core.dao.mapper;

import fun.pullock.incentive.core.dao.model.EventDO;

public interface EventMapper {

    int deleteByPrimaryKey(Long id);

    int insert(EventDO row);

    int insertSelective(EventDO row);

    EventDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EventDO row);

    int updateByPrimaryKey(EventDO row);

    EventDO selectByCode(String code);
}