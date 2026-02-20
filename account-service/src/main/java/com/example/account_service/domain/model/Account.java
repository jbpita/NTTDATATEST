package com.example.account_service.domain.model;

import java.math.BigDecimal;
import java.util.UUID;


public class Account {

    private final UUID id;
    private final UUID customerId;
    private final String type;
    private final String number;
    private BigDecimal balance;
    private final boolean active;

    public Account(UUID id, UUID customerId, String number, String type) {
        this.id = id;
        this.customerId = customerId;
        this.number = number;
        this.balance = BigDecimal.ZERO;
        this.active = true;
        this.type = type;
    }


    public Account(UUID id, UUID customerId, String number, String type, BigDecimal balance) {
        this.id = id;
        this.customerId = customerId;
        this.number = number;
        this.active = true;
        this.type = type;
        this.balance = balance;
    }

    public Account(UUID id, UUID customerId, String number, String type, BigDecimal balance, boolean active) {
        this.id = id;
        this.customerId = customerId;
        this.number = number;
        this.active = active;
        this.type = type;
        this.balance = balance;
    }

    public static Account create(UUID customerId, String type) {
        return new Account(
                UUID.randomUUID(),
                customerId,
                generateAccountNumber(),
                type
        );
    }

    public void debit(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Saldo no disponible");
        }
        this.balance = this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    private static String generateAccountNumber() {
        return "ACC-" + System.currentTimeMillis();
    }

    public UUID getId() { return id; }
    public UUID getCustomerId() { return customerId; }
    public String getNumber() { return number; }
    public BigDecimal getBalance() { return balance; }
    public boolean isActive() { return active; }
    public String getType() {
        return type;
    }
}
