package fun.pullock.activity.core.strategy.task.period.unit;

import fun.pullock.activity.api.enums.TaskPeriodUnit;
import org.springframework.stereotype.Component;

@Component
public class TaskPeriodUnit6Handler implements TaskPeriodUnitHandler {

    @Override
    public TaskPeriodUnit getTaskPeriodUnit() {
        return TaskPeriodUnit.UNIT_6;
    }
}
