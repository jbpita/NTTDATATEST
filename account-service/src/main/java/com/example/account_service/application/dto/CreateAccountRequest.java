package com.example.account_service.application.dto;


import java.math.BigDecimal;
import java.util.UUID;

public record CreateAccountRequest(
        String number,
        String type,
        BigDecimal initialBalance,
        boolean state,
        UUID customerId
) {}
