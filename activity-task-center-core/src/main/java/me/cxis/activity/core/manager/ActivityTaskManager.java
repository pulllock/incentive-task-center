package me.cxis.activity.core.manager;

import jakarta.annotation.Resource;
import me.cxis.activity.core.dao.ActivityTaskDao;
import me.cxis.activity.core.dao.model.ActivityTaskDO;
import me.cxis.activity.core.model.ActivityTask;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ActivityTaskManager {

    @Resource
    private ActivityTaskDao activityTaskDao;

    public ActivityTask queryByEventId(Long eventId) {
        ActivityTaskDO taskDO = activityTaskDao.queryByEventId(eventId);
        return toActivityTask(taskDO);
    }

    private ActivityTask toActivityTask(ActivityTaskDO source) {
        if (source == null) {
            return null;
        }

        ActivityTask target = new ActivityTask();
        // TODO
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
