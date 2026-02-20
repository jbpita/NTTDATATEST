package com.example.account_service.infrastructure.adapter.persistence.mapper;

import com.example.account_service.domain.model.Account;
import com.example.account_service.infrastructure.adapter.persistence.entity.AccountEntity;

public class AccountPersistenceMapper {

    public static AccountEntity toEntity(Account account) {
        return new AccountEntity(
                account.getId(),
                account.getCustomerId(),
                account.getNumber(),
                account.getBalance(),
                account.getType(),
                account.isActive()
        );
    }

    public static Account toDomain(AccountEntity entity) {
        return new Account(
                entity.getId(),
                entity.getCustomerId(),
                entity.getNumber(),
                entity.getType(),
                entity.getBalance(),
                entity.isActive()
        );
    }
}
