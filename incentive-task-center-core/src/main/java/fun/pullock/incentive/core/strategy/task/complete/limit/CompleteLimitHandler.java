package fun.pullock.incentive.core.strategy.task.complete.limit;

import fun.pullock.incentive.core.enums.CompleteLimitType;

public interface CompleteLimitHandler {

    CompleteLimitType type();

    Boolean reachLimit(CompleteLimitContext context);
}
