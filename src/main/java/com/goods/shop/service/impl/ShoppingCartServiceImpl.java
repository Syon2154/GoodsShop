package com.goods.shop.service.impl;

import com.goods.shop.model.Product;
import com.goods.shop.model.ShoppingCart;
import com.goods.shop.model.User;
import com.goods.shop.repository.ShoppingCartRepository;
import com.goods.shop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public void addProduct(Product product, User user) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user);
        shoppingCart.getProducts().add(product);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartRepository.findByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getProducts().clear();
        shoppingCartRepository.save(shoppingCart);
    }
}
