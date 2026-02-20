package com.example.account_service.application.usecase;

import com.example.account_service.application.dto.ReportResponse;
import com.example.account_service.application.port.AccountRepository;
import com.example.account_service.application.port.MovementRepository;
import com.example.account_service.domain.model.Account;
import com.example.account_service.domain.model.Movement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportUseCase {

    private final AccountRepository accountRepository;
    private final MovementRepository movementRepository;

    public List<ReportResponse> execute(
            String customerId,
            LocalDate startDate,
            LocalDate endDate) {

        UUID customerUUID = UUID.fromString(customerId);

        List<Account> accounts = accountRepository.findByCustomerId(customerUUID);

        return accounts.stream()
                .flatMap(account ->
                        buildReportForAccount(account, startDate, endDate).stream()
                )
                .toList();
    }


    private List<ReportResponse> buildReportForAccount(
            Account account,
            LocalDate startDate,
            LocalDate endDate) {

        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(23, 59, 59);

        List<Movement> movements =
                movementRepository.findByAccountIdAndDateBetween(
                        account.getId(),
                        start,
                        end
                );

        return movements.stream()
                .map(movement -> toResponse(account, movement))
                .toList();
    }

    private ReportResponse toResponse(
            Account account,
            Movement movement) {

        return ReportResponse.builder()
                        .date(movement.getDate())
                        .customerName("N/A") // opcional mejorar con integración customer
                        .accountNumber(account.getNumber())
                        .accountType(account.getType())
                        .initialBalance(account.getBalance())
                        .state(account.isActive())
                        .movementValue(movement.getValue())
                        .availableBalance(movement.getBalance())
                        .build();

    }
}
