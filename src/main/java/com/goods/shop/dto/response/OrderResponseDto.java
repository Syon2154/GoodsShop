package com.goods.shop.dto.response;

import com.goods.shop.model.Order;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {
    private Long id;
    private Long userId;
    private List<ProductResponseDto> productDtos;
    private Order.OrderStatus status;
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

    public List<ProductResponseDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(List<ProductResponseDto> productDtos) {
        this.productDtos = productDtos;
    }

    public Order.OrderStatus getStatus() {
        return status;
    }

    public void setStatus(Order.OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
}
