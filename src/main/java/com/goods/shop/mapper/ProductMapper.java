package com.goods.shop.mapper;

import com.goods.shop.dto.request.ProductRequestDto;
import com.goods.shop.dto.response.ProductResponseDto;
import com.goods.shop.model.Category;
import com.goods.shop.model.Product;
import com.goods.shop.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setName(product.getName());
        responseDto.setCategoryName(product.getCategory().getName());
        responseDto.setPrice(product.getPrice());
        responseDto.setQuantity(product.getQuantity());
        return responseDto;
    }

    public Product mapToModel(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setName(requestDto.getName());
        Category category = categoryService.get(requestDto.getCategoryId());
        product.setCategory(category);
        product.setPrice(requestDto.getPrice());
        product.setQuantity(requestDto.getQuantity());
        return product;
    }
}
