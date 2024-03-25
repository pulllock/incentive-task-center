package fun.pullock.incentive.core.dao.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDO {

    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer status;

    private String name;

    private String description;

    private Integer type;

    private Integer sequenceWeight;

    private Integer completeType;

    private Integer afterCompleteType;

    private Integer completeEngageWay;

    private String events;

    private String completeRule;

    private String completeLimitRule;

    private String afterCompleteRule;

    private String completeEngageRule;

    private String buttonConfig;

    private String redirectLinkConfig;
}