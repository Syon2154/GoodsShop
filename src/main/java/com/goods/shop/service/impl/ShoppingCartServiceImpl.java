package com.goods.shop.service.impl;

import com.goods.shop.exception.ProductNotEnoughthQuantityException;
import com.goods.shop.model.Product;
import com.goods.shop.model.ShoppingCart;
import com.goods.shop.model.User;
import com.goods.shop.repository.ShoppingCartRepository;
import com.goods.shop.service.ProductService;
import com.goods.shop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    @Override
    public void addProduct(Product product, User user) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user);
        if (product.getQuantity() < 1) {
            throw new ProductNotEnoughthQuantityException("Product:"
                    + product.getName() + " out of stock");
        }
        shoppingCart.getProducts().add(product);
        shoppingCartRepository.save(shoppingCart);
        int quantity = product.getQuantity();
        quantity--;
        product.setQuantity(quantity);
        productService.update(product);
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
