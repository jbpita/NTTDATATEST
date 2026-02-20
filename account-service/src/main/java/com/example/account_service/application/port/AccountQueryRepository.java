package com.example.account_service.application.port;

import com.example.account_service.domain.model.Account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountQueryRepository {

    Optional<Account> findById(UUID id);

    List<Account> findByCustomerId(UUID customerId);
}
