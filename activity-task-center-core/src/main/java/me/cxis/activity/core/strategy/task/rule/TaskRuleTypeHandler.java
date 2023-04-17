package me.cxis.activity.core.strategy.task.rule;

import me.cxis.activity.api.enums.TaskRuleType;
import me.cxis.activity.core.model.ActivityTask;

public interface TaskRuleTypeHandler {

    TaskRuleType getTaskRuleType();

    void executeTask(Long userId, String source, ActivityTask task);
}
