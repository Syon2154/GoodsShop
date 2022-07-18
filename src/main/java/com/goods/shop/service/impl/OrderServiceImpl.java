package com.goods.shop.service.impl;

import com.goods.shop.model.Order;
import com.goods.shop.model.ShoppingCart;
import com.goods.shop.model.User;
import com.goods.shop.repository.OrderRepository;
import com.goods.shop.service.OrderService;
import com.goods.shop.service.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ShoppingCartService shoppingCartService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ShoppingCartService shoppingCartService) {
        this.orderRepository = orderRepository;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public Order createOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.setUser(shoppingCart.getUser());
        order.setOrderTime(LocalDateTime.now());
        order.setProducts(shoppingCart.getProducts());
        order.setStatus(Order.OrderStatus.UNPAID);
        orderRepository.save(order);
        shoppingCartService.clear(shoppingCart);
        return order;
    }

    @Override
    public Order get(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order with id "
                + id + " doesn't exists"));
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public String payOrder(Long id) {
        Order order = get(id);
        order.setStatus(Order.OrderStatus.PAID);
        update(order);
        return "Order: " + id + " successfully paid";
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderRepository.findAllByUser(user);
    }
}
