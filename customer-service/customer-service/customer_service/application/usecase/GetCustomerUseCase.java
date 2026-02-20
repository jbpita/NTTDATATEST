package com.example.customer_service.application.usecase;

import com.example.customer_service.application.dto.CustomerResponse;
import com.example.customer_service.application.mapper.CustomerMapper;
import com.example.customer_service.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetCustomerUseCase {

    private final CustomerRepository repository;

    public Mono<CustomerResponse> getById(UUID id) {
        return Mono.fromCallable(() ->
                        repository.findById(id)
                                .map(CustomerMapper::toResponse)
                                .orElseThrow(() -> new RuntimeException("Customer not found"))
                )
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<CustomerResponse> getAll() {
        return Mono.fromCallable(repository::findAll)
                .flatMapMany(Flux::fromIterable)
                .map(CustomerMapper::toResponse)
                .subscribeOn(Schedulers.boundedElastic());
    }
}