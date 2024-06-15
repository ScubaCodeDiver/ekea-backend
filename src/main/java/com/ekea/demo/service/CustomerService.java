package com.ekea.demo.service;

import com.ekea.demo.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getCustomers();
    Optional<Customer> getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    void deleteCustomer(Long id);
}
