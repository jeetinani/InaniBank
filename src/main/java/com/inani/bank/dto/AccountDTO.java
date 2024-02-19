package com.inani.bank.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import com.inani.bank.domain.Account;
import com.inani.bank.enums.AccountType;

public class AccountDTO implements Serializable {

    private String accountNumber;
    private String accountHolder;
    private BigDecimal balance;
    private Date openingDate;
    private AccountType accountType;
    private String email;
    private String mobile;
    private String address;
    private String panCardNumber;
    private String aadharCardNumber;

    public AccountDTO(Long accountNumber, String accountHolder, BigDecimal balance, Date openingDate,
            AccountType accountType, String email, String mobile, String address, String panCardNumber,
            String aadharCardNumber) {
        this.accountNumber = accountNumber != null ? "ACC" + Long.toString(accountNumber) : null;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.openingDate = openingDate;
        this.accountType = accountType;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.panCardNumber = panCardNumber;
        this.aadharCardNumber = aadharCardNumber;
    }

    public AccountDTO(Account account) {
        this.accountNumber = "ACC" + Long.toString(account.getAccountNumber());
        this.accountHolder = account.getAccountHolder();
        this.balance = account.getBalance();
        this.openingDate = new Date(account.getOpeningDate().getTime());
        this.accountType = account.getAccountType();
        this.email = account.getEmail();
        this.mobile = account.getMobile();
        this.address = account.getAddress();
        this.panCardNumber = account.getPanCardNumber();
        this.aadharCardNumber = account.getAadharCardNumber();
    }

    public Account toDomainObject() {
        return new Account(
                this.getAccountNumber() != null ? Long.parseLong(this.getAccountNumber().substring(3)) : null,
                this.getAccountHolder(),
                this.getBalance(),
                new Timestamp(this.getOpeningDate().getTime()), this.getAccountType(), this.getEmail(),
                this.getMobile(), this.getAddress(), this.getPanCardNumber(), this.getAadharCardNumber(), null);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
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

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
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
}