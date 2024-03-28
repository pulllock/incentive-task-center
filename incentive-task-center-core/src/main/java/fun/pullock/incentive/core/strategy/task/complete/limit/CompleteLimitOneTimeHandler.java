package fun.pullock.incentive.core.strategy.task.complete.limit;

import fun.pullock.incentive.core.enums.CompleteLimitType;
import fun.pullock.incentive.core.model.dto.CompleteRecordDTO;
import fun.pullock.incentive.core.service.CompleteRecordService;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static fun.pullock.incentive.core.enums.CompleteLimitType.ONE_TIME;

@Component
public class CompleteLimitOneTimeHandler implements CompleteLimitHandler {

    @Resource
    private CompleteRecordService completeRecordService;

    @Override
    public CompleteLimitType type() {
        return ONE_TIME;
    }

    @Override
    public Boolean reachLimit(CompleteLimitContext context) {
        List<CompleteRecordDTO> records = completeRecordService.queryByUserTask(
                context.getUserId(),
                context.getTask().getId()
        );
        return CollectionUtils.isNotEmpty(records);
    }
}
