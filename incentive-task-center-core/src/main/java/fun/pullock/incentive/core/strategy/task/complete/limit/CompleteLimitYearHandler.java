package fun.pullock.incentive.core.strategy.task.complete.limit;

import fun.pullock.incentive.api.model.TriggerParam;
import fun.pullock.incentive.core.enums.CompleteLimitType;
import fun.pullock.incentive.core.model.dto.TaskDTO;
import org.springframework.stereotype.Component;

import static fun.pullock.incentive.core.enums.CompleteLimitType.YEAR;

@Component
public class CompleteLimitYearHandler implements CompleteLimitHandler {

    @Override
    public CompleteLimitType type() {
        return YEAR;
    }

    @Override
    public Boolean reachLimit(TriggerParam triggerParam, TaskDTO task) {
        // TODO
        return null;
    }
}