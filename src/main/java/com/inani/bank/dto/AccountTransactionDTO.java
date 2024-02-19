package com.inani.bank.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.inani.bank.domain.AccountTransaction;
import com.inani.bank.enums.AccountTransactionType;

public class AccountTransactionDTO implements Serializable {

    private AccountTransactionType accountTransactionType;
    private BigDecimal amount;
    private String description;
    private Timestamp transactionTime;
    private String accountNumber;
    private String transactionId;

    public AccountTransactionDTO(AccountTransactionType accountTransactionType, BigDecimal amount, String description,
            Timestamp transactionTime, String accountNumber, Long transactionId) {
        this.accountTransactionType = accountTransactionType;
        this.amount = amount;
        this.description = description;
        this.transactionTime = transactionTime;
        this.accountNumber = accountNumber;
        this.transactionId = transactionId != null ? "TRN" + Long.toString(transactionId) : null;
    }

    public AccountTransactionDTO(AccountTransaction accountTransaction) {
        this.accountNumber = "ACC" + Long.toString(accountTransaction.getAccount().getAccountNumber());
        this.amount = accountTransaction.getAmount();
        this.description = accountTransaction.getDescription();
        this.transactionTime = accountTransaction.getTransactionTime();
        this.accountTransactionType = accountTransaction.getAccountTransactionType();
        this.transactionId = "TRN" + Long.toString(accountTransaction.getId());
    }

    public AccountTransaction toDomainObject() {
        return new AccountTransaction(this.accountTransactionType, this.amount, this.description, this.transactionTime,
                null);
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

}