package com.binsoft.film.config;

import com.binsoft.film.config.properties.RestProperties;
import com.binsoft.film.controller.common.filter.AuthFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    @ConditionalOnProperty(prefix = RestProperties.REST_PREFIX,name="auth-open",havingValue = "true",matchIfMissing = true)
    public AuthFilter jwtAuthenticationTokenFilter(){
        return new AuthFilter();
    }
}
