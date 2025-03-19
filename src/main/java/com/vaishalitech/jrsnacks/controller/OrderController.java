package com.vaishalitech.jrsnacks.controller;
import com.vaishalitech.jrsnacks.dto.OrderRequest;
import com.vaishalitech.jrsnacks.dto.OrderResponse;
import com.vaishalitech.jrsnacks.dto.OrderStatusUpdateRequest;
import com.vaishalitech.jrsnacks.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // GET /api/orders - Fetch all orders (Admin)
    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    // GET /api/orders/{id} - Fetch specific order
    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // POST /api/orders - Place new order
    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    // PUT /api/orders/{id} - Update order status (Admin)
    @PutMapping("/{id}")
    public OrderResponse updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatusUpdateRequest statusUpdateRequest) {
        return orderService.updateOrderStatus(id, statusUpdateRequest);
    }
}

