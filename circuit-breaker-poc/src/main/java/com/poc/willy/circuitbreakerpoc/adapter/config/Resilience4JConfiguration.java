//package com.poc.willy.circuitbreakerpoc.adapter.config;
//
//import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
//import io.github.resilience4j.timelimiter.TimeLimiterConfig;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
//import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
//import org.springframework.cloud.client.circuitbreaker.Customizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.Duration;
//
//@Configuration
//public class Resilience4JConfiguration {
//
//    private static final Logger log = LoggerFactory.getLogger(Resilience4JConfiguration.class);
//
//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {
//        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
//                .failureRateThreshold(20)
//                .waitDurationInOpenState(Duration.ofMillis(3000))
//                .enableAutomaticTransitionFromOpenToHalfOpen()
////                .maxWaitDurationInHalfOpenState(Duration.ofMillis(1500))
//                .slidingWindowSize(2)
////                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
////                .recordExceptions(HttpStatusCodeException.class)
//                .build();
//
//        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
//                .timeoutDuration(Duration.ofSeconds(5))
//                .build();
//
//        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//                .timeLimiterConfig(timeLimiterConfig)
//                .circuitBreakerConfig(circuitBreakerConfig)
//                .build());
//
//    }
//}