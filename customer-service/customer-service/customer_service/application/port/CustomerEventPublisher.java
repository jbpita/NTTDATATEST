package com.example.customer_service.application.port;

import com.example.customer_service.domain.model.Customer;

public interface CustomerEventPublisher {

    void publishCustomerCreated(Customer customer);
}
