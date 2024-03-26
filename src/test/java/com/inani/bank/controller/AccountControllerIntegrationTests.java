package com.inani.bank.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AccountControllerIntegrationTests {

    @Autowired
    private AccountController accountController;

    @Test
    public void testTokenValidation() {
        assertThrows(Exception.class, () -> accountController.getAccounts("invalid token"));
    }

}