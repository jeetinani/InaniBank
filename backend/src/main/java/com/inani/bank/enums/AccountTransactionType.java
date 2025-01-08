package com.inani.bank.enums;

public enum AccountTransactionType {
    CREDIT("Credit"), DEBIT("Debit");

    private String value;

    private AccountTransactionType(String val) {
        this.value = val;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public AccountTransactionType fromValue(String value) {
        if (value != null && !value.trim().isEmpty()) {
            for (AccountTransactionType accountTransactionType : values()) {
                if (accountTransactionType.value.equalsIgnoreCase(value)) {
                    return accountTransactionType;
                }
            }
        }
        return null;
    }
}
