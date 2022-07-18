package com.goods.shop.mapper;

import com.goods.shop.dto.request.CategoryRequestDto;
import com.goods.shop.dto.response.CategoryResponseDto;
import com.goods.shop.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponseDto mapToDto(Category category) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(category.getId());
        responseDto.setName(category.getName());
        return responseDto;

    }

    public Category mapToModel(CategoryRequestDto requestDto) {
        Category category = new Category();
        category.setName(requestDto.getName());
        return category;
    }
}
