package fun.pullock.incentive.core.dao;

import fun.pullock.incentive.core.dao.model.TaskExecutionLogDO;
import jakarta.annotation.Resource;
import fun.pullock.incentive.core.dao.mapper.TaskExecutionLogMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TaskExecutionLogDao {

    @Resource
    private TaskExecutionLogMapper taskExecutionLogMapper;

    public int insertSelective(TaskExecutionLogDO log) {
        return taskExecutionLogMapper.insertSelective(log);
    }
}
