package com.example.account_service.application.usecase;

import com.example.account_service.application.dto.CreateMovementRequest;
import com.example.account_service.application.dto.MovementResponse;
import com.example.account_service.application.port.AccountRepository;
import com.example.account_service.application.port.MovementRepository;
import com.example.account_service.domain.model.Account;
import com.example.account_service.domain.model.Movement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateMovementUseCase {

    private final AccountRepository accountRepository;
    private final MovementRepository movementRepository;

    @Transactional
    public MovementResponse execute(CreateMovementRequest request) {

        if (request.value().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Value must be greater than zero");
        }

        Account account = accountRepository.findById(request.accountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if ("DEBIT".equals(request.type())) {
            account.debit(request.value());
        } else if ("CREDIT".equals(request.type())) {
            account.credit(request.value());
        } else {
            throw new IllegalArgumentException("Invalid movement type");
        }

        accountRepository.save(account);

        Movement movement = new Movement(
                UUID.randomUUID(),
                LocalDateTime.now(),
                request.type(),
                request.value(),
                account.getBalance(),
                account.getId()
        );

        Movement savedMovement = movementRepository.save(movement);

        return new MovementResponse(
                savedMovement.getId(),
                savedMovement.getDate(),
                savedMovement.getType(),
                savedMovement.getValue(),
                savedMovement.getBalance(),
                savedMovement.getAccountId()
        );
    }
}
