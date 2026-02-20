package com.example.customer_service.application.dto;

import lombok.Builder;

@Builder
public record CreateCustomerRequest(
        String name,
        String gender,
        String identification,
        String address,
        String phone,
        String password,
        Boolean status
) {
}
