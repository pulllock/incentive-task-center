package fun.pullock.activity.core.dao;

import jakarta.annotation.Resource;
import fun.pullock.activity.core.dao.mapper.ActivityEventMapper;
import fun.pullock.activity.core.dao.model.ActivityEventDO;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityEventDao {

    @Resource
    private ActivityEventMapper activityEventMapper;

    public ActivityEventDO queryByCode(String code) {
        return activityEventMapper.selectByCode(code);
    }
}
