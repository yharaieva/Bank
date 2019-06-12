package com.haraieva.bank.dto;

import java.time.LocalDateTime;

public class TransactionDto {

    private Long id;
    private Long recipientId;
    private Long senderId;
    private Double amount;
    private LocalDateTime createdTime;

    public TransactionDto(){
    }

    public TransactionDto(Long id, Long recipientId, Long senderId, Double amount, LocalDateTime createdTime) {
        this.id = id;
        this.recipientId = recipientId;
        this.senderId = senderId;
        this.amount = amount;
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recepientId) {
        this.recipientId = recepientId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
