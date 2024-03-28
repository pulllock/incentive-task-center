package fun.pullock.incentive.core.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskCompleteResult {

    public TaskCompleteResult() {
    }

    public TaskCompleteResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Long taskId;

    private Integer code;

    private String message;
}
