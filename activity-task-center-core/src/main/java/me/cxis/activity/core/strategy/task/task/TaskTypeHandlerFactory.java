package me.cxis.activity.core.strategy.task.task;

import jakarta.annotation.Resource;
import me.cxis.activity.api.enums.TaskType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskTypeHandlerFactory {

    @Resource
    private List<TaskTypeHandler> taskTypeHandlers;

    public TaskTypeHandler getTaskTypeHandler(TaskType taskType) {
        if (taskType == null) {
            return null;
        }

        return taskTypeHandlers.stream()
                .filter(handler -> handler.getTaskType() == taskType)
                .findFirst()
                .orElse(null);
    }
}
