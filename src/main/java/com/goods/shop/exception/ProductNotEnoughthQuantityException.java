package com.goods.shop.exception;

public class ProductNotEnoughthQuantityException extends RuntimeException {
    public ProductNotEnoughthQuantityException(String message) {
        super(message);
    }
}
