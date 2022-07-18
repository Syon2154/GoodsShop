package com.goods.shop.service;

import com.goods.shop.model.Role;

public interface RoleService {
    Role add(Role role);

    Role findByName(Role.RoleName roleName);
}
