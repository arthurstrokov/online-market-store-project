package com.gmail.arthurstrokov.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:database.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:pagepath.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:filepath.properties", ignoreResourceNotFound = true),
})
@ComponentScan(basePackages = {
        "com.gmail.arthurstrokov.config",
        "com.gmail.arthurstrokov.controller",
        "com.gmail.arthurstrokov.service",
        "com.gmail.arthurstrokov.dao",
})
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
