package com.example.account_service.infrastructure.adapter.web.rest;


import com.example.account_service.application.dto.ReportResponse;
import com.example.account_service.application.usecase.ReportUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportUseCase reportUseCase;

    @GetMapping
    public List<ReportResponse> getReport(
            @RequestParam String customerId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate) {

        return reportUseCase.execute(customerId, startDate, endDate);
    }
}
