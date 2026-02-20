package com.example.customer_service.application.port;

import com.example.contracts.event.CustomerCreatedEvent;
import com.example.customer_service.domain.model.Customer;

public interface CustomerEventPublisher {

    void publishCustomerCreated(Customer customer);
    void publish(CustomerCreatedEvent event);
}
