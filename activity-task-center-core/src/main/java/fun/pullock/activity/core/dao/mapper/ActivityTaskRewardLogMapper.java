package fun.pullock.activity.core.dao.mapper;

import fun.pullock.activity.core.dao.model.ActivityTaskRewardLogDO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityTaskRewardLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ActivityTaskRewardLogDO row);

    int insertSelective(ActivityTaskRewardLogDO row);

    ActivityTaskRewardLogDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityTaskRewardLogDO row);

    int updateByPrimaryKey(ActivityTaskRewardLogDO row);

    List<ActivityTaskRewardLogDO> query(@Param("userId") Long userId,
                                      @Param("taskId") Long taskId,
                                      @Param("startDate") LocalDateTime startDate,
                                      @Param("endDate") LocalDateTime endDate
    );

    int updateStatus(@Param("id") Long id, @Param("status") int status);
}