package fun.pullock.activity.core.strategy.task.task;

import fun.pullock.activity.api.enums.TaskType;
import fun.pullock.activity.core.model.ActivityTask;
import org.springframework.stereotype.Component;

@Component
public class TaskType21Handler implements TaskTypeHandler {

    @Override
    public TaskType getTaskType() {
        return TaskType.TYPE_21;
    }

    @Override
    public void executeTask(Long userId, String source, ActivityTask task) {

    }
}