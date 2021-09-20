package com.poc.willy.circuitbreakerpoc.adapter.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(11))
                .setReadTimeout(Duration.ofMillis(1000))
                .build();
    }
}
