package com.example.account_service.application.usecase;

import com.example.account_service.application.dto.AccountResponse;
import com.example.account_service.application.mapper.AccountMapper;
import com.example.account_service.application.port.AccountRepository;
import com.example.account_service.domain.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAccountByIdUseCase {

    private final AccountRepository repository;

    public AccountResponse execute(UUID id) {

        Account account = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return AccountMapper.toResponse(account);
    }
}
