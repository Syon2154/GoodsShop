package com.goods.shop.service.impl;

import com.goods.shop.model.Category;
import com.goods.shop.repository.CategoryRepository;
import com.goods.shop.service.CategoryService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category get(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category with id: "
                        + id + " doesn't exists"));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
