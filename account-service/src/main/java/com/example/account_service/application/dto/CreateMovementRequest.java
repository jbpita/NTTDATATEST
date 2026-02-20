package com.example.account_service.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateMovementRequest(
        UUID accountId,
        String type,
        BigDecimal value
) {}
