package com.tommy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.ServletContext;

/**
 * Created by Tommy on 2017/6/30.
 */
@Configuration
@PropertySource("classpath:/app.properties")
public class SpringConfig {

    @Autowired
    Environment env;

    @Autowired
    ServletContext servletContext;

    @Value("${line_channel_token}")
    public String lineChannelToken;

    @Value("${fb_recipient_id}")
    public String fbRecipientId;

    @Value("${fb_page_token}")
    public String fbPageToken;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }


}
