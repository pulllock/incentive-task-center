package fun.pullock.incentive.core.strategy.task.complete.after;

import fun.pullock.incentive.core.model.reqeust.TriggerParam;
import fun.pullock.incentive.core.enums.AfterCompleteType;
import fun.pullock.incentive.core.model.dto.TaskCompleteResult;
import fun.pullock.incentive.core.model.dto.TaskDTO;

import static fun.pullock.incentive.core.enums.AfterCompleteType.MQ;

public class AfterCompleteMQHandler implements AfterCompleteHandler {

    @Override
    public AfterCompleteType type() {
        return MQ;
    }

    @Override
    public TaskCompleteResult complete(TriggerParam param, TaskDTO task) {
        // TODO
        return null;
    }
}
