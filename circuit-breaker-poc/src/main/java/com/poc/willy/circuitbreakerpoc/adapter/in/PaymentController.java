package com.poc.willy.circuitbreakerpoc.adapter.in;

import com.poc.willy.circuitbreakerpoc.adapter.dto.PaymentRequest;
import com.poc.willy.circuitbreakerpoc.adapter.mapper.RequestMapper;
import com.poc.willy.circuitbreakerpoc.application.port.in.PaymentInput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeoutException;

@RequestMapping("/payment")
@RestController
public class PaymentController {

    public PaymentController(PaymentInput paymentInput, RequestMapper requestMapper) {
        this.paymentInput = paymentInput;
        this.requestMapper = requestMapper;
    }

    private final PaymentInput paymentInput;
    private final RequestMapper requestMapper;

    @PostMapping
    public void order(@RequestBody PaymentRequest paymentRequest) throws TimeoutException {
        paymentInput.order(requestMapper.mapToDto(paymentRequest));
    }
}
