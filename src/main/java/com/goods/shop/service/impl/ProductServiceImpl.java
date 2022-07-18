package com.goods.shop.service.impl;

import com.goods.shop.model.Category;
import com.goods.shop.model.Product;
import com.goods.shop.repository.ProductRepository;
import com.goods.shop.service.ProductService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product get(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product with id: "
                + id + " doesn't exists"));
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        return productRepository.findAll();
    }
}
