package com.inani.bank.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inani.bank.domain.Account;
import com.inani.bank.dto.AccountDTO;
import com.inani.bank.repository.AccountRepository;

@RestController
public class AccountController {

    private AccountRepository accountRepository;
    private static Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/accounts")
    public Account saveAccounts(@RequestBody @Validated AccountDTO accountDTO) throws Exception {
        if (nullCheck(accountDTO)) {
            return accountRepository.save(accountDTO.toDomainObject());
        }
        throw new Exception("account number missing");
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void error(Exception e) {
        LOGGER.error("e.getMessag", e);
    }

    private boolean nullCheck(AccountDTO accountDTO) {
        return accountDTO.getAccountType() != null;
    }

}