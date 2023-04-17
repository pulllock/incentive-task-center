package me.cxis.activity.core.strategy.task.rule;

import jakarta.annotation.Resource;
import me.cxis.activity.api.enums.TaskRuleType;
import me.cxis.activity.core.strategy.task.rule.TaskRuleTypeHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskRuleTypeHandlerFactory {

    @Resource
    private List<TaskRuleTypeHandler> taskRuleTypeHandlers;

    public TaskRuleTypeHandler getTaskRuleTypeHandler(TaskRuleType taskRuleType) {
        if (taskRuleType == null) {
            return null;
        }

        return taskRuleTypeHandlers.stream()
                .filter(handler -> handler.getTaskRuleType() == taskRuleType)
                .findFirst()
                .orElse(null);
    }
}
