package com.example.account_service.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountResponse(
        UUID id,
        UUID customerId,
        String type,
        String number,
        BigDecimal balance,
        boolean state
) {}
