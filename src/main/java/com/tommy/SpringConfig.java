package com.tommy;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Tommy on 2017/6/30.
 */
@Configuration
@PropertySource("classpath:/app.properties")
public class SpringConfig {
}
