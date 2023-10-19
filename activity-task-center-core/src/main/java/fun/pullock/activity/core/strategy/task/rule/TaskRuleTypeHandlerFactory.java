package fun.pullock.activity.core.strategy.task.rule;

import fun.pullock.activity.api.enums.TaskRuleType;
import jakarta.annotation.Resource;
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
