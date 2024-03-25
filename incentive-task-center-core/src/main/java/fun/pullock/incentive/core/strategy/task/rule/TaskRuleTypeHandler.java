package fun.pullock.incentive.core.strategy.task.rule;

import fun.pullock.incentive.core.model.Task;
import fun.pullock.incentive.api.enums.TaskRuleType;

public interface TaskRuleTypeHandler {

    TaskRuleType getTaskRuleType();

    void executeTask(Long userId, String source, Task task);
}
