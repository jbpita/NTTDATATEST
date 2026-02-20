package com.example.customer_service.application.usecase;


import com.example.customer_service.domain.exception.BusinessException;
import com.example.customer_service.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteCustomerUseCase {

    private final CustomerRepository repository;

    public Mono<Object> execute(UUID id) {

        return Mono.fromRunnable(() -> {

            if (repository.findById(id).isEmpty()) {
                throw new BusinessException("Customer not found");
            }

            log.info("Deleting customer with id={}", id);

            repository.deleteById(id);

        }).subscribeOn(Schedulers.boundedElastic());
    }
}
