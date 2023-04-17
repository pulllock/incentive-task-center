package me.cxis.activity.core.strategy.task.rule;

import me.cxis.activity.api.enums.TaskRuleType;
import me.cxis.activity.core.model.ActivityTask;
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
    public void executeTask(Long userId, String source, ActivityTask task) {

    }
}
