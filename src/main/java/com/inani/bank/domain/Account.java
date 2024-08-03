package com.inani.bank.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.inani.bank.enums.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountNumberSequence")
    @GenericGenerator(name = "accountNumberSequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "accountNumberSequence"),
            @Parameter(name = "initial_value", value = "10001"),
            @Parameter(name = "increment_size", value = "1")
    })
    @Column
    private Long accountNumber;

    @Column
    private String accountHolder;

    @Column(columnDefinition = "number(19,2) default 0.0")
    private BigDecimal balance;

    @Column
    private Timestamp openingDate;

    @Enumerated(EnumType.STRING)
    @Column
    private AccountType accountType;

    @Column
    private String email;

    @Column(length = 10)
    private String mobile;

    @Column
    private String address;

    @Column
    private String panCardNumber;

    @Column
    private String aadharCardNumber;

    @Column
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
    private List<AccountTransaction> transactions;

    public Account() {
        // does nothing
    }

    public Account(Long accountNumber, String accountHolder, BigDecimal balance, Timestamp openingDate,
            AccountType accountType, String email, String mobile, String address, String panCardNumber,
            String aadharCardNumber, List<AccountTransaction> transactions) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.openingDate = openingDate;
        this.accountType = accountType;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.panCardNumber = panCardNumber;
        this.aadharCardNumber = aadharCardNumber;
        this.transactions = transactions;
    }

    public Account(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Timestamp getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Timestamp openingDate) {
        this.openingDate = openingDate;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPanCardNumber() {
        return panCardNumber;
    }

    public void setPanCardNumber(String panCardNumber) {
        this.panCardNumber = panCardNumber;
    }

    public String getAadharCardNumber() {
        return aadharCardNumber;
    }

    public void setAadharCardNumber(String aadharCardNumber) {
        this.aadharCardNumber = aadharCardNumber;
    }

    public List<AccountTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<AccountTransaction> transactions) {
        this.transactions = transactions;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = Long.valueOf(String.format("%05d", Long.toString(accountNumber)));
    }
}