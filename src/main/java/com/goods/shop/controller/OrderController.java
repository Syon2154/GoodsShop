package com.goods.shop.controller;

import com.goods.shop.dto.response.OrderResponseDto;
import com.goods.shop.mapper.OrderMapper;
import com.goods.shop.model.ShoppingCart;
import com.goods.shop.model.User;
import com.goods.shop.service.OrderService;
import com.goods.shop.service.ShoppingCartService;
import com.goods.shop.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderService orderService,
                           OrderMapper orderMapper,
                           UserService userService,
                           ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/create")
    public OrderResponseDto createOrder(Authentication auth) {
        String email = auth.getName();
        User user = userService.findByEmail(email);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        return orderMapper.mapToDto(orderService.createOrder(shoppingCart));
    }

    @PutMapping("/pay/{id}")
    public String payOrder(@PathVariable Long id) {
        return orderService.payOrder(id);
    }

    @GetMapping
    List<OrderResponseDto> getOrdersHistory(Authentication auth) {
        String email = auth.getName();
        User user = userService.findByEmail(email);
        return orderService.getOrdersHistory(user)
                .stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
