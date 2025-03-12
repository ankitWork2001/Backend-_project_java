package com.vaishalitech.jrsnacks.repository;
import com.vaishalitech.jrsnacks.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}