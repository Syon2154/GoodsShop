package com.goods.shop.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {
    private Long id;
    private Long userId;
    private List<ProductResponseDto> products;
    private boolean isPaid;
    private LocalDateTime orderTime;

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

    public List<ProductResponseDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponseDto> products) {
        this.products = products;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
}
