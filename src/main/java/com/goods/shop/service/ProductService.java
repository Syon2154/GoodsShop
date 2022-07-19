package com.goods.shop.service;

import com.goods.shop.model.Product;
import java.util.List;

public interface ProductService {

    Product add(Product product);

    Product get(Long id);

    Product update(Product product);

    List<Product> findAllByCategoryName(String categoryName);
}
