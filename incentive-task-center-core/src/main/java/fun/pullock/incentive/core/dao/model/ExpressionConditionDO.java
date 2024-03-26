package fun.pullock.incentive.core.dao.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExpressionConditionDO {

    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String code;

    private String name;

    private String description;
}