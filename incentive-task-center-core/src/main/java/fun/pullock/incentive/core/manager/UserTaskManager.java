package fun.pullock.incentive.core.manager;

import fun.pullock.incentive.core.model.app.param.UserTaskPageReq;
import jakarta.annotation.Resource;
import fun.pullock.incentive.api.model.common.PageQuery;
import fun.pullock.incentive.core.dao.UserTaskDao;
import fun.pullock.incentive.core.dao.model.UserTaskDO;
import fun.pullock.incentive.core.model.UserTask;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserTaskManager {

    @Resource
    private UserTaskDao userTaskDao;

    public List<UserTask> pageQueryUserTasks(PageQuery<UserTaskPageReq> req) {
        List<UserTaskDO> taskDOS = userTaskDao.pageQueryUserTasks(req);
        if (taskDOS == null) {
            return null;
        }

        return taskDOS.stream().map(this::toUserTask).collect(Collectors.toList());
    }

    public int countUserTasks(PageQuery<UserTaskPageReq> req) {
        return userTaskDao.countUserTasks(req);
    }

    private UserTask toUserTask(UserTaskDO source) {
        if (source == null) {
            return null;
        }

        UserTask target = new UserTask();
        // TODO
        return target;
    }
}
