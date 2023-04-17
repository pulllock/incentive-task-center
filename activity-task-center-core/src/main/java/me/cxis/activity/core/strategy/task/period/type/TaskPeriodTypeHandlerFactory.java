package me.cxis.activity.core.strategy.task.period.type;

import jakarta.annotation.Resource;
import me.cxis.activity.api.enums.TaskPeriodType;
import me.cxis.activity.core.strategy.task.period.type.TaskPeriodTypeHandler;
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
