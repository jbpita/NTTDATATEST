package com.example.contracts.event;


import java.time.Instant;
import java.util.UUID;

public record CustomerCreatedEvent(
        UUID customerId,
        String name,
        String identification,
        Instant createdAt
) {}
