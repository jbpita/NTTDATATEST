package com.example.customer_service.application.usecase;


import com.example.customer_service.application.dto.CreateCustomerRequest;
import com.example.customer_service.application.dto.CustomerResponse;
import com.example.customer_service.application.mapper.CustomerMapper;
import com.example.customer_service.application.port.CustomerEventPublisher;
import com.example.customer_service.domain.exception.CustomerAlreadyExistsException;
import com.example.customer_service.domain.model.Customer;
import com.example.customer_service.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;



@Service
@RequiredArgsConstructor
public class CreateCustomerUseCase {
    private final CustomerRepository repository;
    private final CustomerEventPublisher eventPublisher;

    public Mono<CustomerResponse> execute(CreateCustomerRequest request) {

        return Mono.fromCallable(() -> {

                    if (repository.existsByIdentification(request.identification())) {
                        throw new CustomerAlreadyExistsException(request.identification());
                    }

                    Customer customer = CustomerMapper.toDomain(request);
                    Customer saved = repository.save(customer);

                    eventPublisher.publishCustomerCreated(saved);

                    return CustomerMapper.toResponse(saved);
                })
                .subscribeOn(Schedulers.boundedElastic());
    }
}
