package me.cxis.activity.core.dao;

import jakarta.annotation.Resource;
import me.cxis.activity.core.dao.mapper.ActivityTaskMapper;
import me.cxis.activity.core.dao.model.ActivityTaskDO;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityTaskDao {

    @Resource
    private ActivityTaskMapper activityTaskMapper;

    public ActivityTaskDO queryByEventId(Long eventId) {
        return activityTaskMapper.selectByEventId(eventId);
    }
}
