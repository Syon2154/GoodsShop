package com.goods.shop.dto.response;

import java.util.List;

public class ShoppingCartResponseDto {
    private Long id;
    private Long userId;
    private List<ProductResponseDto> productDtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<ProductResponseDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(List<ProductResponseDto> productDtos) {
        this.productDtos = productDtos;
    }
}
