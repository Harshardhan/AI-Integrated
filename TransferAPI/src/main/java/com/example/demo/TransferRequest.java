package com.example.demo;

import jakarta.validation.constraints.Positive;

public class TransferRequest {

    private Long fromId;
    private Long toId;
    
    @Positive(message = "Transfer amount must be positive")

    private double amount;

    public Long getFromId() { return fromId; }
    public void setFromId(Long fromId) { this.fromId = fromId; }

    public Long getToId() { return toId; }
    public void setToId(Long toId) { this.toId = toId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}