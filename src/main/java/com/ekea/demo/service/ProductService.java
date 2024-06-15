package com.ekea.demo.service;

import com.ekea.demo.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getProducts();
    Optional<Product> getProductById(Long id);
    Product createProduct(Product product);
    void deleteProduct(Long id);
}
