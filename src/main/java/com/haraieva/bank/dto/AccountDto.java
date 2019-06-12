package com.haraieva.bank.dto;

public class AccountDto {

    private Long id;
    private Long clientId;
    private Double amount;

    public AccountDto() {
    }

    public AccountDto(Long id, Long clientId, Double amount) {
        this.id = id;
        this.clientId = clientId;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
