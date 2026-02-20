package com.example.account_service.infrastructure.adapter.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "movements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovementEntity {

    @Id
    private UUID id;

    private LocalDateTime date;

    private String type;

    private BigDecimal value;

    private BigDecimal balance;

    private UUID accountId;
}
