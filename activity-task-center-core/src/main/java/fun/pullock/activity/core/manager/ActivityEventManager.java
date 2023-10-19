package fun.pullock.activity.core.manager;

import jakarta.annotation.Resource;
import fun.pullock.activity.core.dao.ActivityEventDao;
import fun.pullock.activity.core.dao.model.ActivityEventDO;
import fun.pullock.activity.core.model.ActivityEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ActivityEventManager {

    @Resource
    private ActivityEventDao activityEventDao;

    public ActivityEvent queryByCode(String eventCode) {
        ActivityEventDO eventDO = activityEventDao.queryByCode(eventCode);
        if (eventCode == null) {
            return null;
        }
        return toActivityEvent(eventDO);
    }

    private ActivityEvent toActivityEvent(ActivityEventDO source) {
        if (source == null) {
            return null;
        }

        ActivityEvent target = new ActivityEvent();
        // TODO
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
