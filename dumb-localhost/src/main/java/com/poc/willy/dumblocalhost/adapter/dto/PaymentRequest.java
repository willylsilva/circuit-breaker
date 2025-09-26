package com.poc.willy.dumblocalhost.adapter.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentRequest {

    private final UUID transactionId;
    private final String name;
    private final int bankId;
    private final BigDecimal amount;

    public PaymentRequest(UUID transactionId, String name, int bankId, BigDecimal amount) {
        this.transactionId = transactionId;
        this.name = name;
        this.bankId = bankId;
        this.amount = amount;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public String getName() {
        return name;
    }

    public int getBankId() {
        return bankId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
