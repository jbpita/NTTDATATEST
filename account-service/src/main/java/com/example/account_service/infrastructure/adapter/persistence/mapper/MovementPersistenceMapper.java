package com.example.account_service.infrastructure.adapter.persistence.mapper;


import com.example.account_service.domain.model.Movement;
import com.example.account_service.infrastructure.adapter.persistence.entity.MovementEntity;

public class MovementPersistenceMapper {

    public static MovementEntity toEntity(Movement movement) {
        return new MovementEntity(
                movement.getId(),
                movement.getDate(),
                movement.getType(),
                movement.getValue(),
                movement.getBalance(),
                movement.getAccountId()
        );
    }

    public static Movement toDomain(MovementEntity entity) {
        return new Movement(
                entity.getId(),
                entity.getDate(),
                entity.getType(),
                entity.getValue(),
                entity.getBalance(),
                entity.getAccountId()
        );
    }
}
