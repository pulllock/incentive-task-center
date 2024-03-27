package fun.pullock.incentive.core.strategy.task.complete.after;

import fun.pullock.incentive.core.model.reqeust.TriggerParam;
import fun.pullock.incentive.core.enums.AfterCompleteType;
import fun.pullock.incentive.core.model.dto.TaskCompleteResult;
import fun.pullock.incentive.core.model.dto.TaskDTO;
import org.springframework.stereotype.Component;

import static fun.pullock.incentive.core.enums.AfterCompleteType.DO_NOTHING;

@Component
public class AfterCompleteDoNothingHandler implements AfterCompleteHandler {

    @Override
    public AfterCompleteType type() {
        return DO_NOTHING;
    }

    @Override
    public TaskCompleteResult complete(TriggerParam param, TaskDTO task) {
        // TODO
        return null;
    }
}
