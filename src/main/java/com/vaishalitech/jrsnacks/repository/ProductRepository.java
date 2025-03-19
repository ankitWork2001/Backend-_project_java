package com.vaishalitech.jrsnacks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaishalitech.jrsnacks.entity.Category;
import com.vaishalitech.jrsnacks.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    public List<Product> findByCategoryId(Category categoryId);
    public List<Product> findByIsLatestTrue();
}