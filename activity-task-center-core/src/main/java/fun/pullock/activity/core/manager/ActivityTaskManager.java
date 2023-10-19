package fun.pullock.activity.core.manager;

import jakarta.annotation.Resource;
import fun.pullock.activity.core.dao.ActivityTaskDao;
import fun.pullock.activity.core.dao.model.ActivityTaskDO;
import fun.pullock.activity.core.model.ActivityTask;
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
