package fun.pullock.incentive.core.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class CompleteRecordDTO {

    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long userId;

    private Long taskId;

    private Integer status;

    private LocalDate completeDate;

    private LocalTime completeTime;

    private String source;

    private String uniqueSourceId;

    private Integer afterCompleteType;

    private String afterCompleteRule;
}
