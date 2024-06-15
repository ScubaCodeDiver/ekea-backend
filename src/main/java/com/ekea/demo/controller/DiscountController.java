package com.ekea.demo.controller;

import com.ekea.demo.model.Discount;
import com.ekea.demo.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/discounts")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @GetMapping
    public List<Discount> getDiscounts() {
        return discountService.getDiscounts();
    }

    @GetMapping("/{id}")
    public Optional<Discount> getDiscountById(@PathVariable Long id) {
        return discountService.getDiscountById(id);
    }

    @PostMapping
    public Discount createDiscount(@RequestBody Discount discount) {
        return discountService.createDiscount(discount);
    }

    @DeleteMapping("/{id}")
    public void deleteDiscount(@PathVariable Long id) {
        discountService.deleteDiscount(id);
    }

    @GetMapping("/category/{categoryId}")
    public Optional<Discount> getDiscountByCategoryId(@PathVariable Long categoryId) {
        return discountService.getDiscountByCategoryId(categoryId);
    }
}
