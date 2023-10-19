package fun.pullock.activity.core.strategy.task.period.type;

import fun.pullock.activity.api.enums.TaskPeriodType;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskPeriodTypeHandlerFactory {
    
    @Resource
    private List<TaskPeriodTypeHandler> taskPeriodTypeHandlers;
    
    public TaskPeriodTypeHandler getTaskPeriodTypeHandler(TaskPeriodType taskPeriodType) {
        if (taskPeriodType == null) {
            return null;
        }
        
        return taskPeriodTypeHandlers.stream()
                .filter(handler -> handler.getTaskPeriodType() == taskPeriodType)
                .findFirst()
                .orElse(null);
    }
}
