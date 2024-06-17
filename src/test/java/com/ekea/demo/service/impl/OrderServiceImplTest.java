package com.ekea.demo.service.impl;

import com.ekea.demo.model.*;
import com.ekea.demo.repository.CustomerRepository;
import com.ekea.demo.repository.OrderRepository;
import com.ekea.demo.repository.ProductRepository;
import com.ekea.demo.service.DiscountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private DiscountService discountService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CustomerRepository customerRepository;

    private Customer memberCustomer;
    private Customer nonMemberCustomer;
    private Product carpet;
    private Product chiliHotDog;
    private Product wardrobe;
    private Product vegetarianHotDog;
    private Discount furnitureDiscount;
    private Discount foodDiscount;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        memberCustomer = new Customer();
        memberCustomer.setId(1L);
        memberCustomer.setName("Mathias Rabattersen");
        memberCustomer.setIsMember(true);

        nonMemberCustomer = new Customer();
        nonMemberCustomer.setId(2L);
        nonMemberCustomer.setName("Ian Fullprisson");
        nonMemberCustomer.setIsMember(false);

        carpet = new Product();
        carpet.setId(1L);
        carpet.setDescription("Carpet");
        carpet.setPrice(1000.0);
        carpet.setCategory(new Category());
        carpet.getCategory().setId(1L);

        chiliHotDog = new Product();
        chiliHotDog.setId(2L);
        chiliHotDog.setDescription("Chili Hot Dog");
        chiliHotDog.setPrice(30.0);
        chiliHotDog.setCategory(new Category());
        chiliHotDog.getCategory().setId(2L);

        wardrobe = new Product();
        wardrobe.setId(3L);
        wardrobe.setDescription("Wardrobe");
        wardrobe.setPrice(4000.0);
        wardrobe.setCategory(new Category());
        wardrobe.getCategory().setId(3L);

        vegetarianHotDog = new Product();
        vegetarianHotDog.setId(4L);
        vegetarianHotDog.setDescription("Vegetarian Hot Dog");
        vegetarianHotDog.setPrice(20.0);
        vegetarianHotDog.setCategory(new Category());
        vegetarianHotDog.getCategory().setId(2L);

        furnitureDiscount = new Discount();
        furnitureDiscount.setId(1L);
        furnitureDiscount.setCategory(wardrobe.getCategory());
        furnitureDiscount.setDiscountPercentage(20.0);

        foodDiscount = new Discount();
        foodDiscount.setId(2L);
        foodDiscount.setCategory(chiliHotDog.getCategory());
        foodDiscount.setDiscountPercentage(50.0);
    }

    @Test
    public void testCreateOrderForNonMember() {
        Order order = new Order();
        order.setCustomer(nonMemberCustomer);
        Set<OrderItem> orderItems = new HashSet<>();

        OrderItem carpetItem = new OrderItem();
        carpetItem.setProduct(carpet);
        carpetItem.setQuantity(1);
        orderItems.add(carpetItem);

        OrderItem chiliHotDogItem = new OrderItem();
        chiliHotDogItem.setProduct(chiliHotDog);
        chiliHotDogItem.setQuantity(1);
        orderItems.add(chiliHotDogItem);

        order.setOrderItems(orderItems);

        when(customerRepository.findById(nonMemberCustomer.getId())).thenReturn(Optional.of(nonMemberCustomer));
        when(productRepository.findById(carpet.getId())).thenReturn(Optional.of(carpet));
        when(productRepository.findById(chiliHotDog.getId())).thenReturn(Optional.of(chiliHotDog));
        when(orderRepository.save(order)).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);
        assertEquals(1030.0, createdOrder.getTotal());
    }

    @Test
    public void testCreateOrderForMember() {
        Order order = new Order();
        order.setCustomer(memberCustomer);
        Set<OrderItem> orderItems = new HashSet<>();

        OrderItem wardrobeItem = new OrderItem();
        wardrobeItem.setProduct(wardrobe);
        wardrobeItem.setQuantity(1);
        orderItems.add(wardrobeItem);

        OrderItem carpetItem = new OrderItem();
        carpetItem.setProduct(carpet);
        carpetItem.setQuantity(1);
        orderItems.add(carpetItem);

        OrderItem vegetarianHotDogItem = new OrderItem();
        vegetarianHotDogItem.setProduct(vegetarianHotDog);
        vegetarianHotDogItem.setQuantity(1);
        orderItems.add(vegetarianHotDogItem);

        order.setOrderItems(orderItems);

        when(customerRepository.findById(memberCustomer.getId())).thenReturn(Optional.of(memberCustomer));
        when(productRepository.findById(wardrobe.getId())).thenReturn(Optional.of(wardrobe));
        when(productRepository.findById(carpet.getId())).thenReturn(Optional.of(carpet));
        when(productRepository.findById(vegetarianHotDog.getId())).thenReturn(Optional.of(vegetarianHotDog));
        when(discountService.getDiscountByCategoryId(wardrobe.getCategory().getId())).thenReturn(Optional.of(furnitureDiscount));
        when(discountService.getDiscountByCategoryId(vegetarianHotDog.getCategory().getId())).thenReturn(Optional.of(foodDiscount));
        when(orderRepository.save(order)).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);
        assertEquals(4210.0, createdOrder.getTotal()); // Wardrobe: 4000 - 800 = 3200, Carpet: 1000, Hot Dog: 20 - 10 = 10
    }
}
