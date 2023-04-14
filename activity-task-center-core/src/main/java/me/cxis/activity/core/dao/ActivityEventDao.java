package me.cxis.activity.core.dao;

import jakarta.annotation.Resource;
import me.cxis.activity.core.dao.mapper.ActivityEventMapper;
import me.cxis.activity.core.dao.mapper.ActivityTaskMapper;
import me.cxis.activity.core.dao.model.ActivityEventDO;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityEventDao {

    @Resource
    private ActivityEventMapper activityEventMapper;

    public ActivityEventDO queryByCode(String code) {
        return activityEventMapper.selectByCode(code);
    }
}
