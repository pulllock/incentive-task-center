package fun.pullock.incentive.core.dao.mapper;

import fun.pullock.incentive.core.dao.model.TriggerLogDO;
import org.apache.ibatis.annotations.Param;

public interface TriggerLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TriggerLogDO row);

    int insertSelective(TriggerLogDO row);

    TriggerLogDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TriggerLogDO row);

    int updateByPrimaryKey(TriggerLogDO row);

    TriggerLogDO selectByUniqueKey(
            @Param("userId") Long userId,
            @Param("source") String source,
            @Param("uniqueSourceId") String uniqueSourceId
    );
}