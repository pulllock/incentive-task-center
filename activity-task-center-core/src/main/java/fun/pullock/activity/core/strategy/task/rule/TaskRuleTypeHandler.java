package fun.pullock.activity.core.strategy.task.rule;

import fun.pullock.activity.core.model.ActivityTask;
import fun.pullock.activity.api.enums.TaskRuleType;

public interface TaskRuleTypeHandler {

    TaskRuleType getTaskRuleType();

    void executeTask(Long userId, String source, ActivityTask task);
}
