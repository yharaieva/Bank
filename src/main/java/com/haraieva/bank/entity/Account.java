package com.haraieva.bank.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @OneToMany(mappedBy = "recipient", fetch = FetchType.LAZY)
    private List<Transaction> receivedTransactions = new ArrayList<>();


    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private List<Transaction> sentTransactions = new ArrayList<>();

    public void addSentTransaction(Transaction transaction) {
        transaction.setSender(this);
        sentTransactions.add(transaction);
        setAmount(getAmount() - transaction.getAmount());
    }

    public void addReceivedTransaction(Transaction transaction) {
        transaction.setRecipient(this);
        receivedTransactions.add(transaction);
        setAmount(getAmount() + transaction.getAmount());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<Transaction> getReceivedTransaction() {
        return receivedTransactions;
    }

    public void setReceivedTransaction(List<Transaction> receivedTransaction) {
        this.receivedTransactions = receivedTransaction;
    }

    public List<Transaction> getSentTransactions() {
        return sentTransactions;
    }

    public void setSentTransactions(List<Transaction> sentTransactions) {
        this.sentTransactions = sentTransactions;
    }
}
