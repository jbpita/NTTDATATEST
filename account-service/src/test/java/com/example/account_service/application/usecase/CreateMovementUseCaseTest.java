package com.example.account_service.application.usecase;

import com.example.account_service.application.dto.CreateMovementRequest;
import com.example.account_service.application.dto.MovementResponse;
import com.example.account_service.application.port.AccountRepository;
import com.example.account_service.application.port.MovementRepository;
import com.example.account_service.domain.model.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CreateMovementUseCaseTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private MovementRepository movementRepository;

    @InjectMocks
    private CreateMovementUseCase useCase;

    @Test
    void should_credit_account_successfully() {

        UUID accountId = UUID.randomUUID();

        Account account = new Account(
                accountId,
                UUID.randomUUID(),
                "ACC-001",
                "SAVINGS",
                BigDecimal.valueOf(1000)
        );

        when(accountRepository.findById(accountId))
                .thenReturn(Optional.of(account));

        when(accountRepository.save(any()))
                .thenReturn(account);

        when(movementRepository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CreateMovementRequest request =
                new CreateMovementRequest(accountId, "CREDIT", BigDecimal.valueOf(500));

        // 👇 LLAMADA NORMAL
        MovementResponse response = useCase.execute(request);

        assertEquals(BigDecimal.valueOf(1500), response.balance());
    }
}