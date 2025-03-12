package com.ecommerce.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private User user;

    @ManyToMany
    private List<Product> products;

    private double totalAmount;
    private String status;

    // Getters and Setters
}
