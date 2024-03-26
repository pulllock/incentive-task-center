package fun.pullock.incentive.core.dao.mapper;

import fun.pullock.incentive.core.dao.model.ExpressionConditionDO;

public interface ExpressionConditionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ExpressionConditionDO row);

    int insertSelective(ExpressionConditionDO row);

    ExpressionConditionDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExpressionConditionDO row);

    int updateByPrimaryKey(ExpressionConditionDO row);
}