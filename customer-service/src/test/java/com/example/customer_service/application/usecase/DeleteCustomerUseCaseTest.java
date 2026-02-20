package com.example.customer_service.application.usecase;

import com.example.customer_service.domain.exception.BusinessException;
import com.example.customer_service.domain.model.Customer;
import com.example.customer_service.domain.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteCustomerUseCaseTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private DeleteCustomerUseCase useCase;

    @Test
    void shouldDeleteCustomerSuccessfully() {

        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.of(mock(Customer.class)));

        StepVerifier.create(useCase.execute(id))
                .verifyComplete();

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {

        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        StepVerifier.create(useCase.execute(id))
                .expectError(BusinessException.class)
                .verify();

        verify(repository, never()).deleteById(any());
    }
}
