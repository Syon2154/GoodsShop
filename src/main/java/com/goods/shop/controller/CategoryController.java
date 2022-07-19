package com.goods.shop.controller;

import com.goods.shop.dto.request.CategoryRequestDto;
import com.goods.shop.dto.response.CategoryResponseDto;
import com.goods.shop.mapper.CategoryMapper;
import com.goods.shop.model.Category;
import com.goods.shop.service.CategoryService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService,
                              CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    public CategoryResponseDto add(@RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.mapToModel(categoryRequestDto);
        return categoryMapper.mapToDto(categoryService.add(category));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        return categoryMapper.mapToDto(categoryService.get(id));
    }

    @GetMapping
    public List<CategoryResponseDto> findAll() {
        return categoryService.findAll().stream()
                .map(categoryMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
