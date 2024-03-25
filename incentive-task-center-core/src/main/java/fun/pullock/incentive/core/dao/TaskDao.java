package fun.pullock.incentive.core.dao;

import fun.pullock.incentive.core.dao.mapper.TaskMapper;
import fun.pullock.incentive.core.dao.model.TaskDO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDao {

    @Resource
    private TaskMapper taskMapper;

    public TaskDO queryByEventId(Long eventId) {
        return taskMapper.selectByEventId(eventId);
    }
}
