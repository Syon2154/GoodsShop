package com.goods.shop.service.impl;

import com.goods.shop.model.Role;
import com.goods.shop.repository.RoleRepository;
import com.goods.shop.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findByName(Role.RoleName roleName) {
        return roleRepository.findRoleByRoleName(roleName);
    }
}
