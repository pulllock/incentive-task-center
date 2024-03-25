package fun.pullock.incentive.core.strategy.task.period.unit;

import fun.pullock.incentive.api.enums.TaskPeriodUnit;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskPeriodUnitHandlerFactory {
    
    @Resource
    private List<TaskPeriodUnitHandler> taskPeriodUnitHandlers;
    
    public TaskPeriodUnitHandler getTaskPeriodUnitHandler(TaskPeriodUnit taskPeriodUnit) {
        if (taskPeriodUnit == null) {
            return null;
        }
        
        return taskPeriodUnitHandlers.stream()
                .filter(handler -> handler.getTaskPeriodUnit() == taskPeriodUnit)
                .findFirst()
                .orElse(null);
    }
}
