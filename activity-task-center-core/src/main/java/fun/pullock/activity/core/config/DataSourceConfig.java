package fun.pullock.activity.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"fun.pullock.activity.core.dao.mapper"})
public class DataSourceConfig {
}
