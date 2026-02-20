package com.example.account_service.infrastructure.adapter.persistence.respository;

import com.example.account_service.infrastructure.adapter.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataAccountRepository
        extends JpaRepository<AccountEntity, UUID> {

    List<AccountEntity> findByCustomerId(UUID customerId);
}