package me.cxis.activity.core.manager;

import jakarta.annotation.Resource;
import me.cxis.activity.api.model.common.PageQuery;
import me.cxis.activity.core.dao.ActivityTaskDao;
import me.cxis.activity.core.dao.ActivityUserTaskDao;
import me.cxis.activity.core.dao.model.ActivityUserTaskDO;
import me.cxis.activity.core.model.ActivityUserTask;
import me.cxis.activity.core.model.app.param.UserTaskPageReq;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActivityUserTaskManager {

    @Resource
    private ActivityUserTaskDao activityUserTaskDao;

    public List<ActivityUserTask> pageQueryUserTasks(PageQuery<UserTaskPageReq> req) {
        List<ActivityUserTaskDO> taskDOS = activityUserTaskDao.pageQueryUserTasks(req);
        if (taskDOS == null) {
            return null;
        }

        return taskDOS.stream().map(this::toActivityUserTask).collect(Collectors.toList());
    }

    public int countUserTasks(PageQuery<UserTaskPageReq> req) {
        return activityUserTaskDao.countUserTasks(req);
    }

    private ActivityUserTask toActivityUserTask(ActivityUserTaskDO source) {
        if (source == null) {
            return null;
        }

        ActivityUserTask target = new ActivityUserTask();
        // TODO
        return target;
    }
}
