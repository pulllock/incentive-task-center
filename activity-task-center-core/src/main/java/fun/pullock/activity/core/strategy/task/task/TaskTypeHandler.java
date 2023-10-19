package fun.pullock.activity.core.strategy.task.task;

import fun.pullock.activity.api.enums.TaskType;
import fun.pullock.activity.core.model.ActivityTask;

public interface TaskTypeHandler {

    TaskType getTaskType();

    void executeTask(Long userId, String source, ActivityTask task);
}
