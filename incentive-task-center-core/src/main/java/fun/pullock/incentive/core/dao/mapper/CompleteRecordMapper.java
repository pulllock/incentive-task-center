package fun.pullock.incentive.core.dao.mapper;

import fun.pullock.incentive.core.dao.model.CompleteRecordDO;

public interface CompleteRecordMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CompleteRecordDO row);

    int insertSelective(CompleteRecordDO row);

    CompleteRecordDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompleteRecordDO row);

    int updateByPrimaryKeyWithBLOBs(CompleteRecordDO row);

    int updateByPrimaryKey(CompleteRecordDO row);
}