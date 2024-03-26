package fun.pullock.incentive.core.model.dto;

import lombok.Data;

@Data
public class TaskCompleteRuleDTO {

    private String event;

    /**
     * 1-条件
     * 2-表达式
     */
    private Integer type;

    private String condition;

    private String operator;

    private Object value;

    private String expression;
}
