package com.inani.bank.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inani.bank.enums.AccountTransactionType;

@Entity
@JsonIgnoreProperties("account")
public class AccountTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private AccountTransactionType accountTransactionType;

    @Column
    private BigDecimal amount;

    @Column
    private String description;

    @Column
    private Timestamp transactionTime;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Account account;

    public AccountTransaction() {
    }

    

    public AccountTransaction(AccountTransactionType accountTransactionType, BigDecimal amount, String description,
            Timestamp transactionTime, Account account) {
        this.accountTransactionType = accountTransactionType;
        this.amount = amount;
        this.description = description;
        this.transactionTime = transactionTime;
        this.account = account;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountTransactionType getAccountTransactionType() {
        return accountTransactionType;
    }

    public void setAccountTransactionType(AccountTransactionType accountTransactionType) {
        this.accountTransactionType = accountTransactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Timestamp transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}