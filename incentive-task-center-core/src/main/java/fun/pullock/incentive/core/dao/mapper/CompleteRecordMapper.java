package fun.pullock.incentive.core.dao.mapper;

import fun.pullock.incentive.core.dao.model.CompleteRecordDO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

public interface CompleteRecordMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CompleteRecordDO row);

    int insertSelective(CompleteRecordDO row);

    CompleteRecordDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompleteRecordDO row);

    int updateByPrimaryKey(CompleteRecordDO row);

    List<CompleteRecordDO> selectByUserTask(
            @Param("userId") Long userId,
            @Param("taskId") Long taskId
    );

    List<CompleteRecordDO> selectByUserTaskDate(
            @Param("userId") Long userId,
            @Param("taskId") Long taskId,
            @Param("completeDateStart") LocalDate completeDateStart,
            @Param("completeDateEnd")  LocalDate completeDateEnd
    );

    int updateStatus(
            @Param("id") Long id,
            @Param("oldStatus") Integer oldStatus,
            @Param("newStatus") int newStatus
    );
}