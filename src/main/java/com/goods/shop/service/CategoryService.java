package com.goods.shop.service;

import com.goods.shop.model.Category;
import java.util.List;

public interface CategoryService {
    Category add(Category category);

    Category get(Long id);

    List<Category> findAll();
}
