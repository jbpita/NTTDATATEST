package com.example.customer_service.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class Person {

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected String gender;

    @Column(nullable = false, unique = true)
    protected String identification;

    @Column(nullable = false)
    protected String address;

    @Column(nullable = false)
    protected String phone;
}
