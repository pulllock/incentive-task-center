package fun.pullock.incentive.core.strategy.task.complete.limit;

import fun.pullock.incentive.api.model.TriggerParam;
import fun.pullock.incentive.core.enums.CompleteLimitType;
import fun.pullock.incentive.core.model.dto.TaskDTO;

public interface CompleteLimitHandler {

    CompleteLimitType type();

    Boolean reachLimit(TriggerParam triggerParam, TaskDTO task);
}
