package com.ekea.demo.service;

import com.ekea.demo.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getOrders();
    Optional<Order> getOrderById(Long id);
    Order createOrder(Order order);
    void deleteOrder(Long id);
}
