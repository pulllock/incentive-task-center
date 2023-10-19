package fun.pullock.activity.core.dao;

import fun.pullock.activity.core.dao.mapper.ActivityTaskMapper;
import fun.pullock.activity.core.dao.model.ActivityTaskDO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityTaskDao {

    @Resource
    private ActivityTaskMapper activityTaskMapper;

    public ActivityTaskDO queryByEventId(Long eventId) {
        return activityTaskMapper.selectByEventId(eventId);
    }
}
