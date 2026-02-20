package com.example.account_service.application.port;

import com.example.account_service.domain.model.Movement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface MovementRepository {

    Movement save(Movement movement);

    List<Movement> findByAccountId(UUID accountId);

    List<Movement> findByAccountIdAndDateBetween(
            UUID accountId,
            LocalDateTime start,
            LocalDateTime end
    );
}
