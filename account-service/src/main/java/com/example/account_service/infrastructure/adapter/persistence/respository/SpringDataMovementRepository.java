package com.example.account_service.infrastructure.adapter.persistence.respository;

import com.example.account_service.infrastructure.adapter.persistence.entity.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SpringDataMovementRepository
        extends JpaRepository<MovementEntity, UUID> {

    List<MovementEntity> findByAccountId(UUID accountId);

    List<MovementEntity> findByAccountIdAndDateBetween(
            UUID accountId,
            LocalDateTime start,
            LocalDateTime end
    );
}