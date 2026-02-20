package com.example.account_service.application.mapper;

import com.example.account_service.application.dto.AccountResponse;
import com.example.account_service.domain.model.Account;

public class AccountMapper {

    public static AccountResponse toResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getCustomerId(),
                account.getType(),
                account.getNumber(),
                account.getBalance(),
                account.isActive()
        );
    }
}
