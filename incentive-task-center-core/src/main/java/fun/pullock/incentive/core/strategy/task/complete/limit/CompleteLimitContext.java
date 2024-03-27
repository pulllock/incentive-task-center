package fun.pullock.incentive.core.strategy.task.complete.limit;

import fun.pullock.incentive.api.model.reqeust.TriggerParam;
import fun.pullock.incentive.core.model.dto.TaskDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompleteLimitContext {

    public CompleteLimitContext(TriggerParam triggerParam, TaskDTO task, LocalDateTime now) {
        this.triggerParam = triggerParam;
        this.task = task;
        this.now = now;
    }

    private TriggerParam triggerParam;

    private TaskDTO task;

    private LocalDateTime now;
}
