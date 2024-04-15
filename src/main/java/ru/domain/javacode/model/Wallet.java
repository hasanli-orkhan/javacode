package ru.domain.javacode.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotNull
    @Column(name = "balance")
    private BigDecimal balance;

    public Wallet(BigDecimal balance) {
        this.balance = balance;
    }

    public Wallet() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
