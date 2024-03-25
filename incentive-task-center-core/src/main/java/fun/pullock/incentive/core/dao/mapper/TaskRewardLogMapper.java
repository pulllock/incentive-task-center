package fun.pullock.incentive.core.dao.mapper;

import fun.pullock.incentive.core.dao.model.TaskRewardLogDO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRewardLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TaskRewardLogDO row);

    int insertSelective(TaskRewardLogDO row);

    TaskRewardLogDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskRewardLogDO row);

    int updateByPrimaryKey(TaskRewardLogDO row);

    List<TaskRewardLogDO> query(@Param("userId") Long userId,
                                      @Param("taskId") Long taskId,
                                      @Param("startDate") LocalDateTime startDate,
                                      @Param("endDate") LocalDateTime endDate
    );

    int updateStatus(@Param("id") Long id, @Param("status") int status);
}