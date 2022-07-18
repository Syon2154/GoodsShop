package com.goods.shop.controller;

import com.goods.shop.dto.response.ShoppingCartResponseDto;
import com.goods.shop.mapper.ShoppingCartMapper;
import com.goods.shop.model.Product;
import com.goods.shop.model.User;
import com.goods.shop.service.ProductService;
import com.goods.shop.service.ShoppingCartService;
import com.goods.shop.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartMapper shoppingCartMapper;
    private final UserService userService;
    private final ProductService productService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  ShoppingCartMapper shoppingCartMapper,
                                  UserService userService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartMapper = shoppingCartMapper;
        this.userService = userService;
        this.productService = productService;
    }

    @PutMapping("/products/add")
    public void addToCart(Authentication auth, @RequestParam Long productId) {
        UserDetails details = (UserDetails) auth.getPrincipal();
        String email = details.getUsername();
        User user = userService.findByEmail(email);
        Product product = productService.get(productId);
        shoppingCartService.addProduct(product, user);
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication auth) {
        UserDetails details = (UserDetails) auth.getPrincipal();
        String email = details.getUsername();
        User user = userService.findByEmail(email);
        return shoppingCartMapper.mapToDto(shoppingCartService.getByUser(user));
    }
}
