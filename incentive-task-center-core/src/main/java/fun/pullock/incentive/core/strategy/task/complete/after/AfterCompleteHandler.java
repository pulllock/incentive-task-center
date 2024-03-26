package fun.pullock.incentive.core.strategy.task.complete.after;

import fun.pullock.incentive.api.model.TriggerParam;
import fun.pullock.incentive.core.enums.AfterCompleteType;
import fun.pullock.incentive.core.model.dto.TaskCompleteResult;
import fun.pullock.incentive.core.model.dto.TaskDTO;

public interface AfterCompleteHandler {

    AfterCompleteType type();

    TaskCompleteResult complete(TriggerParam param, TaskDTO task);
}
