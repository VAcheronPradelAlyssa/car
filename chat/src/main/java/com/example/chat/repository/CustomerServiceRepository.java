package com.example.chat.repository;

import com.example.chat.entity.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerServiceRepository extends JpaRepository<CustomerService, Long> {
    java.util.Optional<CustomerService> findByUsername(String username);
}
