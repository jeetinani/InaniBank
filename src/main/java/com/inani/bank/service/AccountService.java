package com.inani.bank.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inani.bank.domain.Account;
import com.inani.bank.repository.AccountRepository;

@Service
public class AccountService {
    
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account>getAccount(Long accountNumber){
        return this.accountRepository.findById(accountNumber);
    }
}
