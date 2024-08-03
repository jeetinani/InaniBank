package com.inani.bank.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inani.bank.enums.AccountTransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIgnoreProperties("account")
public class AccountTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionIdSequence")
    @GenericGenerator(name = "transactionIdSequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "transactionIdSequence"),
            @Parameter(name = "initial_value", value = "101"),
            @Parameter(name = "increment_size", value = "1")
    })
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