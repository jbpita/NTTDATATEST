package com.example.account_service.application.usecase;

import com.example.account_service.application.dto.AccountResponse;
import com.example.account_service.application.dto.CreateAccountRequest;
import com.example.account_service.application.port.AccountRepository;
import com.example.account_service.domain.model.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateAccountUseCase {

    private final AccountRepository repository;

    public AccountResponse execute(CreateAccountRequest request) {

        Account account = new Account(
                UUID.randomUUID(),
                request.customerId(),
                request.number(),
                request.type(),
                request.initialBalance()
        );

        Account saved = repository.save(account);

        return new AccountResponse(
                saved.getId(),
                saved.getCustomerId(),
                saved.getNumber(),
                saved.getType(),
                saved.getBalance(),
                saved.isActive()
        );
    }

    public void createForCustomer(UUID customerId, String type) {

        if (repository.existsByCustomerId(customerId)) {
            log.info("account_already_exists customerId={}", customerId);
            return;
        }

        Account account = Account.create(customerId, type);

        repository.save(account);

        log.info("account_created accountId={} customerId={}",
                account.getId(),
                customerId);
    }
}
