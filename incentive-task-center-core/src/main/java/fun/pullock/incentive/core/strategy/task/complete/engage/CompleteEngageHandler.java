package fun.pullock.incentive.core.strategy.task.complete.engage;

import fun.pullock.incentive.api.model.reqeust.TriggerParam;
import fun.pullock.incentive.core.enums.CompleteEngageWay;
import fun.pullock.incentive.core.model.dto.TaskCompleteResult;
import fun.pullock.incentive.core.model.dto.TaskDTO;

public interface CompleteEngageHandler {

    CompleteEngageWay type();

    void engage(TriggerParam param, TaskDTO task, TaskCompleteResult completeResult);
}
