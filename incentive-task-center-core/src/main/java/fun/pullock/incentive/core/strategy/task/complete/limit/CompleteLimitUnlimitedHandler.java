package fun.pullock.incentive.core.strategy.task.complete.limit;

import fun.pullock.incentive.core.enums.CompleteLimitType;
import org.springframework.stereotype.Component;

import static fun.pullock.incentive.core.enums.CompleteLimitType.UNLIMITED;

@Component
public class CompleteLimitUnlimitedHandler implements CompleteLimitHandler {

    @Override
    public CompleteLimitType type() {
        return UNLIMITED;
    }

    @Override
    public Boolean reachLimit(CompleteLimitContext context) {
        return false;
    }
}
