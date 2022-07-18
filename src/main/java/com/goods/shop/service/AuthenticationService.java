package com.goods.shop.service;

import com.goods.shop.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
