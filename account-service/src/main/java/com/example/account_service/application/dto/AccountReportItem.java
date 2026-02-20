package com.example.account_service.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record AccountReportItem(
        String accountNumber,
        BigDecimal balance,
        List<MovementResponse> movements,
        LocalDateTime date,
        String customerName,
        String accountType,
        BigDecimal initialBalance,
        boolean state
) {}
