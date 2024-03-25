package fun.pullock.incentive.core.strategy.task.rule;

import fun.pullock.incentive.api.enums.TaskRuleType;
import fun.pullock.incentive.core.model.Task;
import org.springframework.stereotype.Component;

/**
 * 30-周期性（M天N次，每次都算完成）
 */
@Component
public class TaskRuleType30Handler implements TaskRuleTypeHandler {

    @Override
    public TaskRuleType getTaskRuleType() {
        return TaskRuleType.TYPE_30;
    }

    @Override
    public void executeTask(Long userId, String source, Task task) {

    }
}
