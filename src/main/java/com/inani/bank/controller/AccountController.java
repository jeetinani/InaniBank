package com.inani.bank.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inani.bank.domain.Account;
import com.inani.bank.repository.AccountRepository;

@RestController
public class AccountController {

    private AccountRepository accountRepository;
    private static Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @PostMapping("/accounts")
    public Account saveAccounts(@RequestBody @Validated Account account) throws Exception {
        if (nullCheck(account)) {
            return accountRepository.save(account);
        }
        throw new Exception("account number missing");
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void error(Exception e) {
        LOGGER.error("e.getMessag", e);
    }

    private boolean nullCheck(Account account) {
        return account.getAccountType() != null;
    }

}