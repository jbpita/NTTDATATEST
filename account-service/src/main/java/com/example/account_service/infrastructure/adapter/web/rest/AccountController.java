package com.example.account_service.infrastructure.adapter.web.rest;

import com.example.account_service.application.dto.AccountResponse;
import com.example.account_service.application.dto.CreateAccountRequest;
import com.example.account_service.application.usecase.CreateAccountUseCase;
import com.example.account_service.application.usecase.GetAccountByIdUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Tag(name = "Accounts")
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;
    private final GetAccountByIdUseCase getAccountByIdUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(getAccountByIdUseCase.execute(id));
    }

    @PostMapping
    public AccountResponse createAccount(
            @RequestBody CreateAccountRequest request) {

        return createAccountUseCase.execute(request);
    }
}
