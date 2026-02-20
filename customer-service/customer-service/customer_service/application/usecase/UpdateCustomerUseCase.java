package com.example.customer_service.application.usecase;

import com.example.customer_service.application.dto.CustomerResponse;
import com.example.customer_service.application.dto.UpdateCustomerRequest;
import com.example.customer_service.application.mapper.CustomerMapper;
import com.example.customer_service.domain.exception.BusinessException;
import com.example.customer_service.domain.model.Customer;
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
public class UpdateCustomerUseCase {

    private final CustomerRepository repository;

    public Mono<CustomerResponse> execute(UUID id, UpdateCustomerRequest request) {

        return Mono.fromCallable(() -> {

            Customer customer = repository.findById(id)
                    .orElseThrow(() -> new BusinessException("Customer not found"));

            log.info("Updating customer with id={}", id);

            Customer updated = Customer.builder()
                    .id(customer.getId())
                    .name(request.name())
                    .gender(request.gender())
                    .identification(customer.getIdentification())
                    .address(request.address())
                    .phone(request.phone())
                    .password(customer.getPassword())
                    .status(request.status())
                    .build();

            Customer saved = repository.save(updated);

            return CustomerMapper.toResponse(saved);

        }).subscribeOn(Schedulers.boundedElastic());
    }
}
