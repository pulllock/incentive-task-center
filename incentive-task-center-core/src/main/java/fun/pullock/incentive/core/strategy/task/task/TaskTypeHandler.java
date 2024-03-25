package fun.pullock.incentive.core.strategy.task.task;

import fun.pullock.incentive.api.enums.TaskType;
import fun.pullock.incentive.core.model.Task;

public interface TaskTypeHandler {

    TaskType getTaskType();

    void executeTask(Long userId, String source, Task task);
}
