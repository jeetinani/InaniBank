package com.inani.bank.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
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

    @Value("${local.statement.storage.path:./Statements}")
    private String statementPath;

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
        String filePath = statementService.getStatement(statementRequest);
        if (filePath == "Failed") {
            throw new Exception("GenerationFailed");
        } else if (filePath.startsWith("https://objectstorage")) {
            return ResponseEntity.ok(filePath);
        }
        String fileUrl = "/api/statement/files/" + Paths.get(filePath).getFileName().toString();
        return ResponseEntity.ok(fileUrl);
    }

    @GetMapping("/files/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(statementPath).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}