package fun.pullock.incentive.core.strategy.task.complete.engage;

import fun.pullock.incentive.core.model.reqeust.TriggerParam;
import fun.pullock.incentive.core.enums.CompleteEngageWay;
import fun.pullock.incentive.core.model.dto.TaskCompleteResult;
import fun.pullock.incentive.core.model.dto.TaskDTO;

import static fun.pullock.incentive.core.enums.CompleteEngageWay.POPUP;

public class CompleteEngagePopupHandler implements CompleteEngageHandler {

    @Override
    public CompleteEngageWay type() {
        return POPUP;
    }

    @Override
    public void engage(TriggerParam param, TaskDTO task, TaskCompleteResult completeResult) {
        // TODO
    }
}
