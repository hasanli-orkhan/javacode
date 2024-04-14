package ru.domain.javacode.model;

import java.math.BigDecimal;

public class WalletResponse {
    private String message;
    private BigDecimal balance;

    public WalletResponse(String message) {
        this.message = message;
    }

    public WalletResponse(String message, BigDecimal balance) {
        this.message = message;
        this.balance = balance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
