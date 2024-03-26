package fun.pullock.incentive.core.dao.mapper;

import fun.pullock.incentive.core.dao.model.ExpressionOperatorDO;

public interface ExpressionOperatorMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ExpressionOperatorDO row);

    int insertSelective(ExpressionOperatorDO row);

    ExpressionOperatorDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExpressionOperatorDO row);

    int updateByPrimaryKey(ExpressionOperatorDO row);
}