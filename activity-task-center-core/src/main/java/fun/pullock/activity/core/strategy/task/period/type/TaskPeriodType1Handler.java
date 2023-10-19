package fun.pullock.activity.core.strategy.task.period.type;

import fun.pullock.activity.api.enums.TaskPeriodType;
import org.springframework.stereotype.Component;

@Component
public class TaskPeriodType1Handler implements TaskPeriodTypeHandler {

    @Override
    public TaskPeriodType getTaskPeriodType() {
        return TaskPeriodType.TYPE_1;
    }
}