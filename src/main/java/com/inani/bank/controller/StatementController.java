package com.inani.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inani.bank.request.StatementRequest;
import com.inani.bank.service.AccountTransactionService;
import com.inani.bank.service.StatementService;

@RestController
@RequestMapping("/api/statement")
public class StatementController {
    private StatementService statementService;
    private AccountTransactionService accountTransactionService;

    public StatementController(StatementService statementService, AccountTransactionService accountTransactionService) {
        this.statementService = statementService;
        this.accountTransactionService = accountTransactionService;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<String> getMethodName(
            @PathVariable(required = false) String accountId) throws Exception {
        Long accountNumber = Long.valueOf(accountId.substring(3));
        StatementRequest statementRequest = new StatementRequest(
                accountTransactionService.getTop6TransactionsForAccount(accountNumber), accountId);
        String response = statementService.getStatement(statementRequest);
        if (response == "Failed") {
            throw new Exception("GenerationFailed");
        }
        return ResponseEntity.ok(response);
    }
}