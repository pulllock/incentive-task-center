package me.cxis.activity.core.strategy.task.task;

import jakarta.annotation.Resource;
import me.cxis.activity.api.enums.TaskRuleType;
import me.cxis.activity.api.enums.TaskType;
import me.cxis.activity.core.model.ActivityTask;
import me.cxis.activity.core.strategy.task.rule.TaskRuleTypeHandler;
import me.cxis.activity.core.strategy.task.rule.TaskRuleTypeHandlerFactory;
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
