package com.vaishalitech.jrsnacks.repository;

import com.vaishalitech.jrsnacks.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Additional custom queries can be added here if needed.

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}

