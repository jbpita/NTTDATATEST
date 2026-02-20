package com.example.account_service.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record MovementResponse(
        UUID id,
        LocalDateTime date,
        String type,
        BigDecimal value,
        BigDecimal balance,
        UUID accountId
) {}
