package com.example.customer_service.infraestructure.adapter.persitence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.customer_service.domain.model.Customer;

import java.util.UUID;

public interface SpringDataCustomerRepository extends JpaRepository<Customer, UUID> {

    boolean existsByIdentification(String identification);
}
