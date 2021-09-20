package com.poc.willy.circuitbreakerpoc.application;

import com.poc.willy.circuitbreakerpoc.application.dto.PaymentDto;
import com.poc.willy.circuitbreakerpoc.application.port.in.PaymentInput;
import com.poc.willy.circuitbreakerpoc.application.port.out.PaymentOutput;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

@Service
public class PaymentService implements PaymentInput {

    public PaymentService(PaymentOutput paymentOutput) {
        this.paymentOutput = paymentOutput;
    }

    private final PaymentOutput paymentOutput;

    @Override
    public void order(PaymentDto paymentDto) throws TimeoutException {
        paymentOutput.order(paymentDto);
    }
}
