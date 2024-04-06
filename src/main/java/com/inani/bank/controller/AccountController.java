package com.inani.bank.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inani.bank.domain.Account;
import com.inani.bank.dto.AccountDTO;
import com.inani.bank.dto.AccountTransactionDTO;
import com.inani.bank.repository.AccountRepository;
import com.inani.bank.service.AccountTransactionService;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api")
public class AccountController {

    private AccountRepository accountRepository;
    private AccountTransactionService accountTransactionService;
    private static Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    public AccountController(AccountRepository accountRepository, AccountTransactionService accountTransactionService) {
        this.accountRepository = accountRepository;
        this.accountTransactionService = accountTransactionService;
    }

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        //System.out.println("token is " + token);
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/accounts/{accountNumber}")
    public AccountDTO getMethodName(@PathVariable(value = "accountNumber", required = false) String accountId) {
        //System.out.println("token is " + token);
        Long accountNumber = Long.valueOf(accountId.substring(3));
        if (accountRepository.existsById(accountNumber)) {
            return new AccountDTO(accountRepository.findById(accountNumber).get());
        }
        throw new NoSuchElementException("account " + accountNumber + " not found");
    }

    @GetMapping("/accounts/{accountNumber}/transactions")
    public List<AccountTransactionDTO> getTransactions(@PathVariable(value = "accountNumber", required = false) String accountId) {
        //System.out.println("token is " + token);
        Long accountNumber = Long.valueOf(accountId.substring(3));
        if (accountRepository.existsById(accountNumber)) {
            return accountTransactionService.getTransactionsForAccount(accountNumber);
        }
        throw new NoSuchElementException("account " + accountNumber + " not found");
    }

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody @Validated AccountDTO accountDTO) throws Exception {
        if (nullCheck(accountDTO)) {
            return accountRepository.save(accountDTO.toDomainObject());
        }
        throw new Exception("account number missing");
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void error(Exception e) {
        LOGGER.error(e.getMessage(), e);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void noAccount(NoSuchElementException e) {
        LOGGER.error(e.getMessage(), e);
    }

    private boolean nullCheck(AccountDTO accountDTO) {
        return accountDTO.getAccountType() != null;
    }
}