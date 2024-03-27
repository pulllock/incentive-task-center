package fun.pullock.incentive.core.strategy.task.complete.after;

import fun.pullock.incentive.core.model.reqeust.TriggerParam;
import fun.pullock.incentive.core.enums.AfterCompleteType;
import fun.pullock.incentive.core.model.dto.TaskCompleteResult;
import fun.pullock.incentive.core.model.dto.TaskDTO;
import org.springframework.stereotype.Component;

import static fun.pullock.incentive.core.enums.AfterCompleteType.BADGE;

@Component
public class AfterCompleteBadgeHandler implements AfterCompleteHandler {

    @Override
    public AfterCompleteType type() {
        return BADGE;
    }

    @Override
    public TaskCompleteResult complete(TriggerParam param, TaskDTO task) {
        // TODO
        return null;
    }
}
