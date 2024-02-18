package com.inani.bank.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inani.bank.domain.AccountTransaction;
import com.inani.bank.dto.AccountTransactionDTO;
import com.inani.bank.repository.AccountTransactionRepository;
import com.inani.bank.service.AccountService;

@RestController
public class AccountTransactionController {

    private static Logger LOGGER = LoggerFactory.getLogger(AccountTransactionController.class);

    private AccountTransactionRepository accountTransactionRepository;
    private AccountService accountService;

    public AccountTransactionController(AccountTransactionRepository accountTransactionRepository,
            AccountService accountService) {
        this.accountTransactionRepository = accountTransactionRepository;
        this.accountService = accountService;
    }

    @GetMapping("/transactions")
    public List<AccountTransactionDTO> getTransactions() {
        return accountTransactionRepository.findAll().stream().map(AccountTransactionDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/transactions")
    public AccountTransactionDTO saveTransaction(@RequestBody AccountTransactionDTO accountTransactionDTO)
            throws Exception {
        if (nullCheck(accountTransactionDTO)) {
            AccountTransaction accountTransaction = accountTransactionDTO.getDomainObject();
            accountTransaction.setAccount(accountService.getAccount(accountTransactionDTO.getAccountNumber()).get());
            return new AccountTransactionDTO(accountTransactionRepository.save(accountTransaction));
        }
        throw new Exception("couldn't save transaction");
    }

    private boolean nullCheck(AccountTransactionDTO accountTransactionDTO) {
        return accountTransactionDTO.getAccountNumber() != null && accountTransactionDTO.getAmount() != null;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private void returnError(Exception e) {
        LOGGER.error(e.getMessage(), e);
    }

}