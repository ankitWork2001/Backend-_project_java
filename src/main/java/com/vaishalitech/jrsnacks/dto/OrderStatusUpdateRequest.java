package com.vaishalitech.jrsnacks.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusUpdateRequest {
    private String status; // Expects one of: PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED
}
