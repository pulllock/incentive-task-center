package fun.pullock.incentive.core.strategy.task.complete.limit;

import fun.pullock.incentive.core.enums.CompleteLimitType;
import fun.pullock.incentive.core.model.dto.CompleteRecordDTO;
import fun.pullock.incentive.core.service.CompleteRecordService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static fun.pullock.incentive.core.enums.CompleteLimitType.YEAR;
import static fun.pullock.incentive.core.enums.CompleteRecordStatus.DONE;
import static fun.pullock.incentive.core.enums.CompleteRecordStatus.TO_BE_CLAIMED;

@Component
public class CompleteLimitYearHandler implements CompleteLimitHandler {

    @Resource
    private CompleteRecordService completeRecordService;

    @Override
    public CompleteLimitType type() {
        return YEAR;
    }

    @Override
    public CompleteLimitResult reachLimit(CompleteLimitContext context) {
        LocalDate today = context.getNow().toLocalDate();
        LocalDate startDay = today.with(TemporalAdjusters.firstDayOfYear())
                .minusYears(context.getTask().getCompleteLimitRule().getPeriod() - 1);

        List<CompleteRecordDTO> records = completeRecordService.queryByUserTaskDateRange(
                context.getUserId(),
                context.getTask().getId(),
                startDay,
                today
        );

        return new CompleteLimitResult(
                records.size() >= context.getTask().getCompleteLimitRule().getTimes(),
                context.getTask().getCompleteLimitRule().getTimes(),
                (int) records.stream().filter(r -> r.getStatus() == DONE.getStatus()).count(),
                (int) records.stream().filter(r -> r.getStatus() == TO_BE_CLAIMED.getStatus()).count()
        );
    }
}
