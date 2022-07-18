package com.goods.shop.controller;

import com.goods.shop.dto.request.ProductRequestDto;
import com.goods.shop.dto.response.ProductResponseDto;
import com.goods.shop.mapper.ProductMapper;
import com.goods.shop.model.Category;
import com.goods.shop.model.Product;
import com.goods.shop.service.CategoryService;
import com.goods.shop.service.ProductService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ProductResponseDto add(@RequestBody @Valid ProductRequestDto productRequestDto) {
        Product product = productMapper.mapToModel(productRequestDto);
        return productMapper.mapToDto(productService.add(product));
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productMapper.mapToDto(productService.get(id));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setId(id);
        return productMapper.mapToDto(productService.update(product));
    }

    @GetMapping("/categoryId")
    public List<ProductResponseDto> findAllByCategory(@RequestParam Long categoryId) {
        Category category = categoryService.get(categoryId);
        return productService.findAllByCategory(category).stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
