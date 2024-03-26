package fun.pullock.incentive.core.strategy.task.complete.limit;

import fun.pullock.incentive.api.model.TriggerParam;
import fun.pullock.incentive.core.enums.CompleteLimitType;
import fun.pullock.incentive.core.model.dto.TaskDTO;
import org.springframework.stereotype.Component;

import static fun.pullock.incentive.core.enums.CompleteLimitType.MONTH_RELATIVE;

@Component
public class CompleteLimitMonthRelativeHandler implements CompleteLimitHandler {

    @Override
    public CompleteLimitType type() {
        return MONTH_RELATIVE;
    }

    @Override
    public Boolean reachLimit(TriggerParam triggerParam, TaskDTO task) {
        // TODO
        return null;
    }
}