package fun.pullock.activity.core.manager;

import fun.pullock.activity.core.model.app.param.UserTaskPageReq;
import jakarta.annotation.Resource;
import fun.pullock.activity.api.model.common.PageQuery;
import fun.pullock.activity.core.dao.ActivityUserTaskDao;
import fun.pullock.activity.core.dao.model.ActivityUserTaskDO;
import fun.pullock.activity.core.model.ActivityUserTask;
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
