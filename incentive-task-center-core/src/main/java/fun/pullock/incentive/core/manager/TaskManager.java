package fun.pullock.incentive.core.manager;

import jakarta.annotation.Resource;
import fun.pullock.incentive.core.dao.TaskDao;
import fun.pullock.incentive.core.dao.model.TaskDO;
import fun.pullock.incentive.core.model.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TaskManager {

    @Resource
    private TaskDao taskDao;

    public Task queryByEventId(Long eventId) {
        TaskDO taskDO = taskDao.queryByEventId(eventId);
        return toTask(taskDO);
    }

    private Task toTask(TaskDO source) {
        if (source == null) {
            return null;
        }

        Task target = new Task();
        // TODO
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
