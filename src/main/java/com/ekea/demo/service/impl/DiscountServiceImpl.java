package com.ekea.demo.service.impl;

import com.ekea.demo.model.Discount;
import com.ekea.demo.repository.DiscountRepository;
import com.ekea.demo.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    public List<Discount> getDiscounts() {
        return discountRepository.findAll();
    }

    public Optional<Discount> getDiscountById(Long id) {
        return discountRepository.findById(id);
    }

    public Discount createDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }

    public Optional<Discount> getDiscountByCategoryId(Long categoryId) {
        return Optional.ofNullable(discountRepository.findByCategory_Id(categoryId));
    }
}
