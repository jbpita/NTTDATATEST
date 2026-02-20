package com.example.customer_service.infraestructure.adapter.rest;

import com.example.customer_service.application.dto.CreateCustomerRequest;
import com.example.customer_service.application.dto.CustomerResponse;
import com.example.customer_service.application.dto.UpdateCustomerRequest;
import com.example.customer_service.application.usecase.CreateCustomerUseCase;
import com.example.customer_service.application.usecase.DeleteCustomerUseCase;
import com.example.customer_service.application.usecase.GetCustomerUseCase;
import com.example.customer_service.application.usecase.UpdateCustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CustomerResponse> create(@RequestBody CreateCustomerRequest request) {
        return createCustomerUseCase.execute(request);
    }

    @GetMapping("/{id}")
    public Mono<CustomerResponse> getById(@PathVariable UUID id) {
        return getCustomerUseCase.getById(id);
    }

    @GetMapping
    public Flux<CustomerResponse> getAll() {
        return getCustomerUseCase.getAll();
    }

    @PutMapping("/{id}")
    public Mono<CustomerResponse> update(
            @PathVariable UUID id,
            @RequestBody UpdateCustomerRequest request) {
        return updateCustomerUseCase.execute(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Object> delete(@PathVariable UUID id) {
        return deleteCustomerUseCase.execute(id);
    }
}
