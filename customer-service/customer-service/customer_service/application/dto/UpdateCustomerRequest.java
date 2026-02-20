package com.example.customer_service.application.dto;

import lombok.Builder;

@Builder
public record UpdateCustomerRequest(
        String name,
        String gender,
        String address,
        String phone,
        Boolean status
) {}
