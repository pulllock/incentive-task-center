package fun.pullock.incentive.core.strategy.task.task;

import fun.pullock.incentive.api.enums.TaskType;
import fun.pullock.incentive.core.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskType21Handler implements TaskTypeHandler {

    @Override
    public TaskType getTaskType() {
        return TaskType.TYPE_21;
    }

    @Override
    public void executeTask(Long userId, String source, Task task) {

    }
}
