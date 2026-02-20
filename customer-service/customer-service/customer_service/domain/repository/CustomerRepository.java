package com.example.customer_service.domain.repository;

import com.example.customer_service.domain.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Customer save(Customer customer);

    Optional<Customer> findById(UUID id);

    List<Customer> findAll();

    void deleteById(UUID id);

    boolean existsByIdentification(String identification);
}