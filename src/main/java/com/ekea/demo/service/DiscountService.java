package com.ekea.demo.service;

import com.ekea.demo.model.Discount;

import java.util.List;
import java.util.Optional;

public interface DiscountService {
    List<Discount> getDiscounts();
    Optional<Discount> getDiscountById(Long id);
    Discount createDiscount(Discount discount);
    void deleteDiscount(Long id);
    Optional<Discount> getDiscountByCategoryId(Long categoryId);
}
