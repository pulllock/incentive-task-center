package fun.pullock.incentive.core.dao.mapper;

import fun.pullock.incentive.core.dao.model.TriggerLogDO;

public interface TriggerLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TriggerLogDO row);

    int insertSelective(TriggerLogDO row);

    TriggerLogDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TriggerLogDO row);

    int updateByPrimaryKeyWithBLOBs(TriggerLogDO row);

    int updateByPrimaryKey(TriggerLogDO row);
}