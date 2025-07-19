package com.accompany.stickyrice.service;

import com.accompany.stickyrice.entity.Role;


public interface RoleService {
    Role findByRoleName(String roleName);
}
