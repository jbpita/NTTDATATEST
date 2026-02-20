package com.example.customer_service.application.usecase;

import com.example.customer_service.application.dto.CreateCustomerRequest;
import com.example.customer_service.application.port.CustomerEventPublisher;
import com.example.customer_service.application.usecase.CreateCustomerUseCase;
import com.example.customer_service.domain.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCustomerUseCaseTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerEventPublisher publisher;

    @InjectMocks
    private CreateCustomerUseCase useCase;

    @Test
    void shouldCreateCustomerSuccessfully() {

        CreateCustomerRequest request = CreateCustomerRequest.builder()
                .name("Jose")
                .gender("Male")
                .identification("123")
                .address("Otavalo")
                .phone("098")
                .password("1234")
                .status(true)
                .build();

        when(repository.existsByIdentification("123")).thenReturn(false);
        when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        StepVerifier.create(useCase.execute(request))
                .expectNextCount(1)
                .verifyComplete();
    }
}
