package com.vaishalitech.jrsnacks.repository;
import com.vaishalitech.jrsnacks.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
