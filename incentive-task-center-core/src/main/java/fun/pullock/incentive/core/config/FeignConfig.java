package fun.pullock.incentive.core.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"fun.pullock"})
public class FeignConfig {
}
