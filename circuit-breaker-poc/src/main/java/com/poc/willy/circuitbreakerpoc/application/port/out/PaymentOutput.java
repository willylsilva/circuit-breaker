package com.poc.willy.circuitbreakerpoc.application.port.out;

import com.poc.willy.circuitbreakerpoc.application.dto.PaymentDto;

import java.util.concurrent.TimeoutException;

public interface PaymentOutput {

    void order(PaymentDto paymentDto) throws TimeoutException;
}
