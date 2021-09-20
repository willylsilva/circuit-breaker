package com.poc.willy.circuitbreakerpoc.application.port.in;

import com.poc.willy.circuitbreakerpoc.application.dto.PaymentDto;

import java.util.concurrent.TimeoutException;

public interface PaymentInput {
    void order(PaymentDto mapToDto) throws TimeoutException;
}
