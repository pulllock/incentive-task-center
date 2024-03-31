package fun.pullock.incentive.core.dynamic.config;

import org.springframework.stereotype.Component;

/**
 * 动态配置，暂时没有使用具体实现，只是模拟
 */
@Component
public class DynamicConfig {

    /**
     * 失败重试的天数
     */
    public Long failedRetryDays = 10L;

    /**
     * 失败重试批次大小
     */
    public Integer failedBatchSize = 100;
}
