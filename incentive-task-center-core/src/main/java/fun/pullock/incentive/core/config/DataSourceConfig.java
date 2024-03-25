package fun.pullock.incentive.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"fun.pullock.incentive.core.dao.mapper"})
public class DataSourceConfig {
}
