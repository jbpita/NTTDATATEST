package com.example.account_service.infrastructure.adapter.persistence;

import com.example.account_service.application.port.MovementRepository;
import com.example.account_service.domain.model.Movement;
import com.example.account_service.infrastructure.adapter.persistence.mapper.MovementPersistenceMapper;
import com.example.account_service.infrastructure.adapter.persistence.respository.SpringDataMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MovementRepositoryAdapter implements MovementRepository {

    private final SpringDataMovementRepository repository;

    @Override
    public Movement save(Movement movement) {
        return MovementPersistenceMapper.toDomain(
                repository.save(MovementPersistenceMapper.toEntity(movement))
        );
    }

    @Override
    public List<Movement> findByAccountId(java.util.UUID accountId) {
        return repository.findByAccountId(accountId)
                .stream()
                .map(MovementPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movement> findByAccountIdAndDateBetween(
            java.util.UUID accountId,
            LocalDateTime start,
            LocalDateTime end) {

        return repository.findByAccountIdAndDateBetween(accountId, start, end)
                .stream()
                .map(MovementPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }
}
