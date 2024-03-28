package fun.pullock.incentive.core.strategy.task.complete.limit;

import fun.pullock.incentive.core.enums.CompleteLimitType;
import fun.pullock.incentive.core.model.dto.CompleteRecordDTO;
import fun.pullock.incentive.core.service.CompleteRecordService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

import static fun.pullock.incentive.core.enums.CompleteLimitType.UNLIMITED;
import static fun.pullock.incentive.core.enums.CompleteRecordStatus.DONE;
import static fun.pullock.incentive.core.enums.CompleteRecordStatus.TO_BE_CLAIMED;

@Component
public class CompleteLimitUnlimitedHandler implements CompleteLimitHandler {

    @Resource
    private CompleteRecordService completeRecordService;

    @Override
    public CompleteLimitType type() {
        return UNLIMITED;
    }

    @Override
    public CompleteLimitResult reachLimit(CompleteLimitContext context) {
        List<CompleteRecordDTO> records = completeRecordService.queryByUserTask(
                context.getUserId(),
                context.getTask().getId()
        );
        return new CompleteLimitResult(
                false,
                null,
                (int) records.stream().filter(r -> r.getStatus() == DONE.getStatus()).count(),
                (int) records.stream().filter(r -> r.getStatus() == TO_BE_CLAIMED.getStatus()).count(),
                records
        );
    }
}
