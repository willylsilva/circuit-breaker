package com.poc.willy.circuitbreakerpoc.adapter.out;

import com.poc.willy.circuitbreakerpoc.adapter.config.AppConfig;
import com.poc.willy.circuitbreakerpoc.application.dto.PaymentDto;
import com.poc.willy.circuitbreakerpoc.application.port.out.PaymentOutput;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeoutException;

@Component
public class PaymentGateway implements PaymentOutput {

    private static final Logger log = LoggerFactory.getLogger(PaymentGateway.class);

    private final AppConfig appConfig;

    public PaymentGateway(AppConfig appConfig, CircuitBreakerFactory circuitBreakerFactory, RestTemplate restTemplate) {
        this.appConfig = appConfig;
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.restTemplate = restTemplate;
    }

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final RestTemplate restTemplate;

    @Override
    public void order(PaymentDto paymentDto) throws TimeoutException {

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create(appConfig.getBankId().get(paymentDto.getBankId()));

        ResponseEntity<Void> response = circuitBreaker.run(() -> restTemplate.exchange(
                        appConfig.getUrl(),
                        HttpMethod.POST,
                        new HttpEntity<>(paymentDto),
                        Void.class)
                , this::fallback
        );

        if (response.getStatusCodeValue() == 504) {
            throw new TimeoutException("deu timeout");
        }
    }

    private ResponseEntity<Void> fallback(Throwable e) {
        log.info("Fallback started...");
        if (e instanceof ResourceAccessException) {
            log.error("Fallback ResourceAccessException");
            throw new HttpServerErrorException(HttpStatus.BAD_GATEWAY, e.getMessage());
//            throw new HttpServerErrorException(HttpStatus.BAD_GATEWAY, "Fallback ResourceAccessException");
        } else if (e instanceof HttpServerErrorException) {
//            log.error("Fallback HttpServerErrorException", e.getCause());
            throw new HttpServerErrorException(HttpStatus.PRECONDITION_FAILED, e.getMessage());
//            throw new HttpServerErrorException(HttpStatus.PRECONDITION_FAILED, "Fallback HttpServerErrorException");
        } else if (e instanceof CallNotPermittedException) {
            throw new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
//            throw new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE, "chamada nao permitida pelo circuit breakers " + ((CallNotPermittedException) e).getCausingCircuitBreakerName());
        }

        log.error("Fallback ", e);
        return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
    }
}
