package com.ekea.demo.service;

import com.ekea.demo.model.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    List<OrderItem> getOrderItems();
    Optional<OrderItem> getOrderItemById(Long id);
    OrderItem createOrderItem(OrderItem orderItem);
    void deleteOrderItem(Long id);
}
