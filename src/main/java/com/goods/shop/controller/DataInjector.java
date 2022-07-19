package com.goods.shop.controller;

import com.goods.shop.model.Category;
import com.goods.shop.model.Product;
import com.goods.shop.model.Role;
import com.goods.shop.model.User;
import com.goods.shop.service.CategoryService;
import com.goods.shop.service.ProductService;
import com.goods.shop.service.RoleService;
import com.goods.shop.service.ShoppingCartService;
import com.goods.shop.service.UserService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInjector {
    private final RoleService roleService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final CategoryService categoryService;
    private final ProductService productService;

    public DataInjector(RoleService roleService,
                        UserService userService,
                        ShoppingCartService shoppingCartService,
                        CategoryService categoryService,
                        ProductService productService) {
        this.roleService = roleService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @PostConstruct
    public void injectData() {
        Role managerRole = new Role();
        managerRole.setRoleName(Role.RoleName.MANAGER);
        roleService.add(managerRole);
        User manager = new User();
        manager.setEmail("manager@shop.com");
        manager.setPassword("managerPass");
        manager.setRoles(Set.of(managerRole));
        userService.add(manager);
        shoppingCartService.registerNewShoppingCart(manager);

        Role clientRole = new Role();
        clientRole.setRoleName(Role.RoleName.CLIENT);
        roleService.add(clientRole);
        User client = new User();
        client.setEmail("client@gmail.com");
        client.setPassword("clientPass");
        client.setRoles(Set.of(clientRole));
        userService.add(client);
        shoppingCartService.registerNewShoppingCart(client);

        Category laptops = new Category();
        laptops.setName("laptops");
        Category phones = new Category();
        phones.setName("phones");
        List<Category> categories = List.of(laptops, phones);
        categories.forEach(categoryService::add);

        Product lenovoThinkPad = new Product();
        lenovoThinkPad.setName("Lenovo ThinkPad X1");
        lenovoThinkPad.setCategory(laptops);
        lenovoThinkPad.setPrice(BigDecimal.valueOf(2500));
        lenovoThinkPad.setQuantity(10);

        Product googlePixel = new Product();
        googlePixel.setName("Google Pixel 6");
        googlePixel.setCategory(phones);
        googlePixel.setPrice(BigDecimal.valueOf(800));
        googlePixel.setQuantity(20);

        List<Product> products = List.of(lenovoThinkPad, googlePixel);
        products.forEach(productService::add);
    }
}
