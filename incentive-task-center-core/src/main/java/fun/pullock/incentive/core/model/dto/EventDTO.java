package fun.pullock.incentive.core.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDTO {

    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String code;

    private Integer status;

    private String name;

    private String description;
}
