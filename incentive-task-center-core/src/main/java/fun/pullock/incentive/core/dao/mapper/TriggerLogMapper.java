package fun.pullock.incentive.core.dao.mapper;

import fun.pullock.incentive.core.dao.model.TriggerLogDO;
import fun.pullock.incentive.core.model.dto.TriggerLogProcessResultDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    boolean updateStatus(
            @Param("newStatus") int newStatus,
            @Param("oldStatus") int oldStatus,
            @Param("id") Long id
    );

    boolean failed(
            @Param("id") Long id,
            @Param("processResult") String processResult
    );

    int updateResult(
            @Param("id") Long id,
            @Param("oldStatus") Integer oldStatus,
            @Param("newStatus") int newStatus,
            @Param("processResult") List<TriggerLogProcessResultDTO> processResults
    );
}