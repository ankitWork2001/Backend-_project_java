package com.vaishalitech.jrsnacks.service;

import com.vaishalitech.jrsnacks.dto.OrderRequest;
import com.vaishalitech.jrsnacks.dto.OrderResponse;
import com.vaishalitech.jrsnacks.dto.OrderStatusUpdateRequest;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(Long id);
    OrderResponse createOrder(OrderRequest orderRequest);
    OrderResponse updateOrderStatus(Long id, OrderStatusUpdateRequest statusUpdateRequest);
}
