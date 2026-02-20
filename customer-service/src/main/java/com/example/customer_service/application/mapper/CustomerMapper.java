package com.example.customer_service.application.mapper;

import com.example.customer_service.application.dto.CreateCustomerRequest;
import com.example.customer_service.application.dto.CustomerResponse;
import com.example.customer_service.domain.model.Customer;

public class CustomerMapper {

    public static Customer toDomain(CreateCustomerRequest request) {
        return Customer.builder()
                .name(request.name())
                .gender(request.gender())
                .identification(request.identification())
                .address(request.address())
                .phone(request.phone())
                .password(request.password())
                .status(request.status())
                .build();
    }

    public static CustomerResponse toResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .gender(customer.getGender())
                .identification(customer.getIdentification())
                .address(customer.getAddress())
                .phone(customer.getPhone())
                .status(customer.getStatus())
                .build();
    }
}
