package me.cxis.activity.core.strategy.task.task;

import me.cxis.activity.api.enums.TaskType;
import me.cxis.activity.core.model.ActivityTask;

public interface TaskTypeHandler {

    TaskType getTaskType();

    void executeTask(Long userId, String source, ActivityTask task);
}
