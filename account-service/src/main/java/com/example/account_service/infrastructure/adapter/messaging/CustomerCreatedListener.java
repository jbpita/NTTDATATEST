package com.example.account_service.infrastructure.adapter.messaging;

import com.example.account_service.application.usecase.CreateAccountUseCase;
import com.example.contracts.event.CustomerCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerCreatedListener {

    private final CreateAccountUseCase createAccountUseCase;

    @KafkaListener(
            topics = "customer-created",
            groupId = "account-service-group"
    )
    public void listen(CustomerCreatedEvent event, String type) {

        log.info("event=customer_created_received customerId={}", event.customerId());

        createAccountUseCase.createForCustomer(event.customerId(),type);
    }
}
