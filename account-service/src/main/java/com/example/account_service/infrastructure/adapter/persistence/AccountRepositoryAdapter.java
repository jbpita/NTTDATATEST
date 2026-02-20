package com.example.account_service.infrastructure.adapter.persistence;

import com.example.account_service.application.port.AccountRepository;
import com.example.account_service.domain.model.Account;
import com.example.account_service.infrastructure.adapter.persistence.mapper.AccountPersistenceMapper;
import com.example.account_service.infrastructure.adapter.persistence.respository.SpringDataAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepository {

    private final SpringDataAccountRepository repository;

    @Override
    public Account save(Account account) {
        return AccountPersistenceMapper.toDomain(
                repository.save(AccountPersistenceMapper.toEntity(account))
        );
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return repository.findById(id)
                .map(AccountPersistenceMapper::toDomain);
    }

    @Override
    public List<Account> findByCustomerId(UUID customerId) {
        return repository.findByCustomerId(customerId)
                .stream()
                .map(AccountPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByCustomerId(UUID customerId){
        return (findByCustomerId(customerId).isEmpty());
    }
}
