package com.ekea.demo.service;

import com.ekea.demo.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getCategories();
    Optional<Category> getCategoryById(Long id);
    Category createCategory(Category category);
    void deleteCategory(Long id);
}
