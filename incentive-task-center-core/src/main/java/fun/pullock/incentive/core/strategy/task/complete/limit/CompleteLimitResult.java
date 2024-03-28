package fun.pullock.incentive.core.strategy.task.complete.limit;

import lombok.Data;

@Data
public class CompleteLimitResult {

    public CompleteLimitResult() {
    }

    public CompleteLimitResult(Boolean reachLimit, Integer limitNumber, Integer completedNumber, Integer toBeClaimedNumber) {
        this.reachLimit = reachLimit;
        this.limitNumber = limitNumber;
        this.completedNumber = completedNumber;
        this.toBeClaimedNumber = toBeClaimedNumber;
    }

    private Boolean reachLimit;

    private Integer limitNumber;

    private Integer completedNumber;

    private Integer toBeClaimedNumber;
}
