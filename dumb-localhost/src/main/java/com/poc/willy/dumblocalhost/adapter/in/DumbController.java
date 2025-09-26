package com.poc.willy.dumblocalhost.adapter.in;

import com.poc.willy.dumblocalhost.adapter.dto.PaymentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.net.http.HttpTimeoutException;

@RestController
@RequestMapping("/payment")
public class DumbController {

    private static final Logger log = LoggerFactory.getLogger(DumbController.class);
    private static int calls = 1;

    @PostMapping
    public void order(@RequestBody PaymentRequest paymentRequest) throws SocketTimeoutException {
        BigDecimal[] remainder = paymentRequest.getAmount().divideAndRemainder(BigDecimal.valueOf(2));
//        if (remainder[1].equals(BigDecimal.ZERO)) {
        if (paymentRequest.getBankId() == 1) {
            if (
//                    (calls > 1 && calls < 300) ||
                    (calls > 400 && calls < 800) ||
                    (calls > 1800 && calls < 2200)
            ) {

                log.info("Throw SocketTimeoutException");
                throw new SocketTimeoutException("Timeout..");

//                Thread.sleep(1100);
//                log.info("Sleep - BankId = {}", paymentRequest.getBankId());
            } else {
                log.info("Without Sleep - BankId = {}", paymentRequest.getBankId());
            }
        }
        calls++;
    }
}
