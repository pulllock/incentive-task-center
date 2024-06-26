package fun.pullock.incentive.core.dao.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserTaskDO {

    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long taskId;

    private Integer status;
}