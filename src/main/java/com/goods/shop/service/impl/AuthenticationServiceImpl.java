package com.goods.shop.service.impl;

import com.goods.shop.model.Role;
import com.goods.shop.model.User;
import com.goods.shop.service.AuthenticationService;
import com.goods.shop.service.RoleService;
import com.goods.shop.service.ShoppingCartService;
import com.goods.shop.service.UserService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     RoleService roleService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.roleService = roleService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByName(Role.RoleName.CLIENT));
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roles);
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
