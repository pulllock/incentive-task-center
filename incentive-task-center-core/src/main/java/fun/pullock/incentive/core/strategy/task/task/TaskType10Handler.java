package fun.pullock.incentive.core.strategy.task.task;

import fun.pullock.incentive.api.enums.TaskRuleType;
import fun.pullock.incentive.api.enums.TaskType;
import fun.pullock.incentive.core.model.Task;
import fun.pullock.incentive.core.strategy.task.rule.TaskRuleTypeHandler;
import fun.pullock.incentive.core.strategy.task.rule.TaskRuleTypeHandlerFactory;
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
    public void executeTask(Long userId, String source, Task task) {
        TaskRuleTypeHandler taskRuleTypeHandler = taskRuleTypeHandlerFactory.getTaskRuleTypeHandler(TaskRuleType.of(task.getType()));
        if (taskRuleTypeHandler == null) {
            return;
        }

        taskRuleTypeHandler.executeTask(userId, source, task);
    }
}
