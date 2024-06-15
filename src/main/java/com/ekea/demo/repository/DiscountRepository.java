package com.ekea.demo.repository;

import com.ekea.demo.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Discount findByCategory_Id(Long categoryId);
}
