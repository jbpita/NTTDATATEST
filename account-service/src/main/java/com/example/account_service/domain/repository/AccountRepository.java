package com.example.account_service.domain.repository;

import com.example.account_service.domain.model.Account;

import java.util.UUID;

public interface AccountRepository {

    void save(Account account);

    boolean existsByCustomerId(UUID customerId);
}
