package fun.pullock.incentive.core.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TriggerLogDTO {

    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long userId;

    private String eventCode;

    private Integer status;

    private String source;

    private String uniqueSourceId;

    private TriggerLogProcessResultDTO processResult;
}
