package com.inani.bank.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.inani.bank.enums.AccountType;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//SEQUENCE, generator = "accountNumberSequence")
    //@GenericGenerator(name = "accountNumberSequence", strategy = "com.inani.bank.sequence.AccountNumberSequenceGenerator")
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

    public Account(Long accountNumber){
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
        this.accountNumber = Long.valueOf(String.format("%05d",Long.toString(accountNumber)));
    }

    /* public void setAccountNumber(String accountNumber) {
        this.accountNumber = Long.valueOf(accountNumber);
    } */

}