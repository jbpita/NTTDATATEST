package com.example.customer_service.application.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CustomerResponse(
        UUID id,
        String name,
        String gender,
        String identification,
        String address,
        String phone,
        Boolean status
) {
}
