package com.goods.shop.service;

import com.goods.shop.model.Product;
import com.goods.shop.model.ShoppingCart;
import com.goods.shop.model.User;

public interface ShoppingCartService {
    void addProduct(Product product, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
