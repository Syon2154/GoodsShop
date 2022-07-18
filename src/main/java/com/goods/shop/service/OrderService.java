package com.goods.shop.service;

import com.goods.shop.model.Order;
import com.goods.shop.model.ShoppingCart;
import com.goods.shop.model.User;
import java.util.List;

public interface OrderService {
    Order createOrder(ShoppingCart shoppingCart);

    Order get(Long id);

    Order update(Order order);

    String payOrder(Long id);

    List<Order> getOrdersHistory(User user);
}
