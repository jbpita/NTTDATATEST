package com.example.customer_service.infraestructure.adapter.messaging;

import com.example.contracts.event.CustomerCreatedEvent;
import com.example.customer_service.application.port.CustomerEventPublisher;
import com.example.customer_service.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaCustomerEventPublisher implements CustomerEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
   // private final KafkaTemplate<String, CustomerCreatedEvent> kafkaTemplate2;
    private static final String TOPIC = "customer-created";

    @Override
    public void publishCustomerCreated(Customer customer) {

        log.info("Publishing customer created event for id: {}", customer.getId());

        kafkaTemplate.send(TOPIC, customer.getId().toString(), customer);
    }

    @Override
    public void publish(CustomerCreatedEvent event) {

        kafkaTemplate.send("customer-created", event.customerId().toString(), event);

        log.info("event=customer_created customerId={}", event.customerId());
    }
}
