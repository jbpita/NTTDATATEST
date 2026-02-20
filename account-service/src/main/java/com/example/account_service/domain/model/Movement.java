package com.example.account_service.domain.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Movement {

    private final UUID id;
    private final LocalDateTime date;
    private final String type;
    private final BigDecimal value;
    private final BigDecimal balance;
    private final UUID accountId;

    public Movement(UUID id,
                    LocalDateTime date,
                    String type,
                    BigDecimal value,
                    BigDecimal balance,
                    UUID accountId) {

        this.id = id;
        this.date = date;
        this.type = type;
        this.value = value;
        this.balance = balance;
        this.accountId = accountId;
    }

    public UUID getId() { return id; }
    public LocalDateTime getDate() { return date; }
    public String getType() { return type; }
    public BigDecimal getValue() { return value; }
    public BigDecimal getBalance() { return balance; }
    public UUID getAccountId() { return accountId; }
}
