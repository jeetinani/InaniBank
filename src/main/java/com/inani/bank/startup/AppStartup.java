package com.inani.bank.startup;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.inani.bank.dto.AccountDTO;
import com.inani.bank.dto.AccountTransactionDTO;
import com.inani.bank.enums.AccountTransactionType;
import com.inani.bank.enums.AccountType;
import com.inani.bank.request.SignupRequest;
import com.inani.bank.service.AccountService;
import com.inani.bank.service.AccountTransactionService;
import com.inani.bank.service.UserService;

@Component
public class AppStartup implements ApplicationListener<ApplicationReadyEvent> {

    private UserService userService;
    private AccountService accountService;
    private AccountTransactionService accountTransactionService;

    @Value("${default.user.name:admin}")
    private String defaultUserName;

    @Value("${default.user.password:admin}")
    private String defaultPassword;

    public AppStartup(UserService userService, AccountService accountService,
            AccountTransactionService accountTransactionService) {
        this.userService = userService;
        this.accountService = accountService;
        this.accountTransactionService = accountTransactionService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (!userService.isDefaultUserPresent()) {
            createDefaultUser();
            String accountNumber = createDefaultAccount();
            createDefaultTransaction(accountNumber);
        }
    }

    private void createDefaultTransaction(String accountNumber) {
        accountTransactionService.saveTransaction(new AccountTransactionDTO(AccountTransactionType.DEBIT,
                new BigDecimal(104.2), "Payment", new Timestamp(System.currentTimeMillis()),
                accountNumber, null));
    }

    private String createDefaultAccount() {
        return accountService
                .saveAccount(new AccountDTO(null, "John Doe", new BigDecimal(1234.56),
                        new Date(System.currentTimeMillis()), AccountType.SAVINGS, new StringBuilder(defaultUserName)
                                .append(".").append(defaultPassword).append("@bank.com").toString(),
                        "9823453452", "asdf", "ABCDS2345", "234652537"));
    }

    private void createDefaultUser() {
        userService.signUp(new SignupRequest(defaultUserName, defaultPassword,
                new StringBuilder(defaultUserName).append(".").append(defaultPassword).append("@bank.com").toString()));
    }
}