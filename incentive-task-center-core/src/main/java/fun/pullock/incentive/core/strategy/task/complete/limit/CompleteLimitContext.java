package fun.pullock.incentive.core.strategy.task.complete.limit;

import fun.pullock.incentive.core.model.dto.TaskDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompleteLimitContext {

    public CompleteLimitContext(Long userId, TaskDTO task, LocalDateTime now) {
        this.userId = userId;
        this.task = task;
        this.now = now;
    }

    private Long userId;


    private TaskDTO task;

    private LocalDateTime now;
}
