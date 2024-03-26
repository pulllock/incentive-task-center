package fun.pullock.incentive.core.strategy.task.complete.after;

import fun.pullock.incentive.api.model.TriggerParam;
import fun.pullock.incentive.core.enums.AfterCompleteType;
import fun.pullock.incentive.core.model.dto.TaskCompleteResult;
import fun.pullock.incentive.core.model.dto.TaskDTO;

import static fun.pullock.incentive.core.enums.AfterCompleteType.POINTS;

public class AfterCompletePointsHandler implements AfterCompleteHandler {

    @Override
    public AfterCompleteType type() {
        return POINTS;
    }

    @Override
    public TaskCompleteResult complete(TriggerParam param, TaskDTO task) {
        // TODO
        return null;
    }
}
