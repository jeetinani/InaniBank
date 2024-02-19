package com.inani.bank.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inani.bank.domain.Account;
import com.inani.bank.dto.AccountTransactionDTO;
import com.inani.bank.repository.AccountTransactionRepository;

@Service
public class AccountTransactionService {

    private AccountTransactionRepository accountTransactionRepository;

    public AccountTransactionService(AccountTransactionRepository accountTransactionRepository) {
        this.accountTransactionRepository = accountTransactionRepository;
    }

    public List<AccountTransactionDTO> getTransactionsForAccount(Long accountNumber) {
        return this.accountTransactionRepository.findByAccount(new Account(accountNumber)).stream()
                .map(AccountTransactionDTO::new).collect(Collectors.toList());
    }
}
