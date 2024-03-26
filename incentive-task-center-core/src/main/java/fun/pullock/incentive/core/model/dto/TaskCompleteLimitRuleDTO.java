package fun.pullock.incentive.core.model.dto;

import lombok.Data;

@Data
public class TaskCompleteLimitRuleDTO {

    /**
     * 类型，取值参考：{@link fun.pullock.incentive.core.enums.CompleteLimitType}
     */
    private Integer type;

    /**
     * 周期
     */
    private Integer period;

    /**
     * 次数
     */
    private Integer times;
}
