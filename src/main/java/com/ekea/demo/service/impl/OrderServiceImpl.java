package com.ekea.demo.service.impl;

import com.ekea.demo.model.Order;
import com.ekea.demo.model.OrderItem;
import com.ekea.demo.repository.OrderRepository;
import com.ekea.demo.service.DiscountService;
import com.ekea.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DiscountService discountService;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Order createOrder(Order order) {
        double total = 0.0;

        for (OrderItem item : order.getOrderItems()) {
            item.setOrder(order);
            AtomicReference<Double> discount = new AtomicReference<>(0.0);

            if (order.getCustomer().getIsMember()) {
                discountService.getDiscountByCategoryId(item.getProduct().getCategory().getId())
                        .ifPresent(d -> discount.set(d.getDiscountPercentage()));
            }

            double itemTotal = item.getPrice() * item.getQuantity();
            double discountedPrice = itemTotal - (itemTotal * discount.get() / 100);
            item.setDiscount(discount.get());
            total += discountedPrice;
        }

        order.setTotal(total);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
