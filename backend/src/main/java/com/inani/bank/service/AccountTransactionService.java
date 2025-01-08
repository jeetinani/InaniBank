package com.inani.bank.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inani.bank.domain.Account;
import com.inani.bank.domain.AccountTransaction;
import com.inani.bank.dto.AccountTransactionDTO;
import com.inani.bank.repository.AccountRepository;
import com.inani.bank.repository.AccountTransactionRepository;

@Service
public class AccountTransactionService {

    private AccountTransactionRepository accountTransactionRepository;
    private AccountRepository accountRepository;

    public AccountTransactionService(AccountTransactionRepository accountTransactionRepository,
            AccountRepository accountRepository) {
        this.accountTransactionRepository = accountTransactionRepository;
        this.accountRepository = accountRepository;
    }

    public List<AccountTransactionDTO> getTransactionsForAccount(Long accountNumber) {
        return this.accountTransactionRepository.findByAccount(new Account(accountNumber)).stream()
                .map(AccountTransactionDTO::new).collect(Collectors.toList());
    }

    public void saveTransaction(AccountTransactionDTO accountTransactionDTO) {
        AccountTransaction accountTransaction = accountTransactionDTO.toDomainObject();
        accountTransaction.setAccount(accountRepository.findById(
                Long.valueOf(accountTransactionDTO.getAccountNumber().substring(3))).get());
        accountTransactionRepository.save(accountTransaction);
    }

    public List<AccountTransactionDTO> getTop6TransactionsForAccount(Long accountNumber) {

        if (accountRepository.existsById(accountNumber)) {
            return this.accountTransactionRepository.findTop6ByAccount(new Account(accountNumber)).stream()
                    .map(AccountTransactionDTO::new).collect(Collectors.toList());
        }
        throw new NoSuchElementException("account " + accountNumber + " not found");

    }
}