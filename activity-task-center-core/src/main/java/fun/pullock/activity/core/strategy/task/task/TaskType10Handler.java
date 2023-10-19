package fun.pullock.activity.core.strategy.task.task;

import fun.pullock.activity.api.enums.TaskRuleType;
import fun.pullock.activity.api.enums.TaskType;
import fun.pullock.activity.core.model.ActivityTask;
import fun.pullock.activity.core.strategy.task.rule.TaskRuleTypeHandler;
import fun.pullock.activity.core.strategy.task.rule.TaskRuleTypeHandlerFactory;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 10-简单类型
 */
@Component
public class TaskType10Handler implements TaskTypeHandler {

    @Resource
    private TaskRuleTypeHandlerFactory taskRuleTypeHandlerFactory;

    @Override
    public TaskType getTaskType() {
        return TaskType.TYPE_10;
    }

    @Override
    public void executeTask(Long userId, String source, ActivityTask task) {
        TaskRuleTypeHandler taskRuleTypeHandler = taskRuleTypeHandlerFactory.getTaskRuleTypeHandler(TaskRuleType.of(task.getType()));
        if (taskRuleTypeHandler == null) {
            return;
        }

        taskRuleTypeHandler.executeTask(userId, source, task);
    }
}
