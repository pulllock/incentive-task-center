package fun.pullock.incentive.core.model.dto;

import lombok.Data;

@Data
public class TaskCompleteResult {

    private Long taskId;

    private Integer code;

    private String message;
}
