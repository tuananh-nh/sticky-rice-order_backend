package com.accompany.stickyrice.service.impl;

import com.accompany.stickyrice.entity.Role;
import com.accompany.stickyrice.repository.RoleRepository;
import com.accompany.stickyrice.service.RoleService;

public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
