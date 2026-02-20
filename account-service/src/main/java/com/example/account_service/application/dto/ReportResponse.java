package com.example.account_service.application.dto;


import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class ReportResponse {

    private LocalDateTime date;
    private String customerName;
    private String accountNumber;
    private String accountType;
    private BigDecimal initialBalance;
    private boolean state;
    private BigDecimal movementValue;
    private BigDecimal availableBalance;
}
