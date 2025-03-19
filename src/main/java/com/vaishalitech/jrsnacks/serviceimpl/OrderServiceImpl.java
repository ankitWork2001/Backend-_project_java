package com.vaishalitech.jrsnacks.serviceimpl;

import com.vaishalitech.jrsnacks.dto.OrderRequest;
import com.vaishalitech.jrsnacks.dto.OrderResponse;
import com.vaishalitech.jrsnacks.dto.OrderStatusUpdateRequest;
import com.vaishalitech.jrsnacks.entity.Order;
import com.vaishalitech.jrsnacks.entity.OrderItem;
import com.vaishalitech.jrsnacks.entity.OrderStatus;
import com.vaishalitech.jrsnacks.repository.OrderRepository;
import com.vaishalitech.jrsnacks.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::mapToOrderResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        return mapToOrderResponse(order);
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .userId(orderRequest.getUserId())
                .totalPrice(orderRequest.getTotalPrice())
                .status(OrderStatus.PENDING)
                .build();

        List<OrderItem> orderItems = orderRequest.getOrderItems().stream().map(itemReq ->
                OrderItem.builder()
                        .productId(itemReq.getProductId())
                        .quantity(itemReq.getQuantity())
                        .price(itemReq.getPrice())
                        .order(order)
                        .build()
        ).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);
        return mapToOrderResponse(savedOrder);
    }

    @Override
    public OrderResponse updateOrderStatus(Long id, OrderStatusUpdateRequest statusUpdateRequest) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        OrderStatus status = OrderStatus.valueOf(statusUpdateRequest.getStatus().toUpperCase());
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        return mapToOrderResponse(updatedOrder);
    }

    private OrderResponse mapToOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setUserId(order.getUserId());
        response.setTotalPrice(order.getTotalPrice());
        response.setStatus(order.getStatus());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());
        if (order.getOrderItems() != null) {
            List<OrderResponse.OrderItemResponse> orderItemResponses = order.getOrderItems().stream().map(item -> {
                OrderResponse.OrderItemResponse itemResponse = new OrderResponse.OrderItemResponse();
                itemResponse.setId(item.getId());
                itemResponse.setProductId(item.getProductId());
                itemResponse.setQuantity(item.getQuantity());
                itemResponse.setPrice(item.getPrice());
                return itemResponse;
            }).collect(Collectors.toList());
            response.setOrderItems(orderItemResponses);
        }
        return response;
    }
}

