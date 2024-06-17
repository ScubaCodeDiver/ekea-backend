package com.ekea.demo.service.impl;

import com.ekea.demo.model.*;
import com.ekea.demo.repository.CustomerRepository;
import com.ekea.demo.repository.OrderRepository;
import com.ekea.demo.repository.ProductRepository;
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

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    @Override
    public Order createOrder(Order order) {
        double total = 0.0;
                
        Customer customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        order.setCustomer(customer);

        for (OrderItem item : order.getOrderItems()) {
            item.setOrder(order);
            AtomicReference<Double> discount = new AtomicReference<>(0.0);

            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            item.setProduct(product);
            item.setPrice(product.getPrice());

            if (customer.getIsMember()) {
                Optional<Double> discountPercentage = discountService.getDiscountByCategoryId(product.getCategory().getId())
                        .map(Discount::getDiscountPercentage);

                discountPercentage.ifPresent(discount::set);
            }

            double itemTotal = item.getPrice() * item.getQuantity();
            double discountedPrice = itemTotal - (itemTotal * discount.get() / 100);
            item.setDiscount(discount.get());
            total += discountedPrice;
        }

        order.setTotal(total);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
