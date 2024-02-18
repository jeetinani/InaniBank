package com.inani.bank.enums;

public enum AccountType {
    SAVINGS("Savings"), CURRENT("Current");

    private String value;

    private AccountType(String val) {
        this.value = val;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public AccountType fromValue(String value) {
        if (value != null && !value.trim().isEmpty()) {
            for (AccountType accountType : values()) {
                if (accountType.value.equalsIgnoreCase(value)) {
                    return accountType;
                }
            }
        }
        return null;
    }
}