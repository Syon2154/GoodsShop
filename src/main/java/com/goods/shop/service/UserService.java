package com.goods.shop.service;

import com.goods.shop.model.User;
import java.util.List;

public interface UserService {
    User add(User user);

    User findByEmail(String email);

    List<User> findAll();
}
