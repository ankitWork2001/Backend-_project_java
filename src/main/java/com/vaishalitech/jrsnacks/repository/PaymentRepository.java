package com.vaishalitech.jrsnacks.repository;

import com.vaishalitech.jrsnacks.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Additional query methods if needed
}