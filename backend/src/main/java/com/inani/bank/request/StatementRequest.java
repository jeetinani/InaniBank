package com.inani.bank.request;

import java.util.List;

import com.inani.bank.dto.AccountTransactionDTO;

public class StatementRequest {
    private List<AccountTransactionDTO> transactions;
    private String accountNumber;

    public StatementRequest(List<AccountTransactionDTO> transactions, String accountNumber) {
        this.transactions = transactions;
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<AccountTransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<AccountTransactionDTO> transactions) {
        this.transactions = transactions;
    }
}
