package com.example.customer_service.domain.exception;


public class CustomerAlreadyExistsException extends BusinessException {

    public CustomerAlreadyExistsException(String identification) {
        super("Customer with identification " + identification + " already exists");
    }
}