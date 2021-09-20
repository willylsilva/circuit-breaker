package com.poc.willy.circuitbreakerpoc.adapter.exception;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> advice(ResourceAccessException e) {
        log.error("Fallback ResourceAccessException");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> advice(HttpServerErrorException e) {
        log.error("HttpServerErrorException, Status: {}, Message: {}", e.getStatusCode().value(), e.getMessage());
        return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<String> advice(TimeoutException e) {
        log.error("Fallback TimeoutException");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.GATEWAY_TIMEOUT);
    }

    @ExceptionHandler(CallNotPermittedException.class)
    public ResponseEntity<String> advice(CallNotPermittedException e) {
        log.error("Fallback CallNotPermittedException");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.GATEWAY_TIMEOUT);
    }

    @ExceptionHandler(NoFallbackAvailableException.class)
    public ResponseEntity<String> advice(NoFallbackAvailableException e) {
        log.error("Fallback NoFallbackAvailableException");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
