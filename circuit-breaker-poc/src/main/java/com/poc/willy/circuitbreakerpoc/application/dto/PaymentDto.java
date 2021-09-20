package com.poc.willy.circuitbreakerpoc.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentDto {

    private UUID transactionId;
    private String name;
    private int bankId;
    private BigDecimal amount;

    public PaymentDto(UUID transactionId, String name, int bankId, BigDecimal amount) {
        this.transactionId = transactionId;
        this.name = name;
        this.bankId = bankId;
        this.amount = amount;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
