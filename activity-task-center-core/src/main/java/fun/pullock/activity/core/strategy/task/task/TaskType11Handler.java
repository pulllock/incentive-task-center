package fun.pullock.activity.core.strategy.task.task;

import fun.pullock.activity.core.model.ActivityTask;
import fun.pullock.activity.api.enums.TaskType;
import org.springframework.stereotype.Component;

@Component
public class TaskType11Handler implements TaskTypeHandler {

    @Override
    public TaskType getTaskType() {
        return TaskType.TYPE_11;
    }

    @Override
    public void executeTask(Long userId, String source, ActivityTask task) {

    }
}
