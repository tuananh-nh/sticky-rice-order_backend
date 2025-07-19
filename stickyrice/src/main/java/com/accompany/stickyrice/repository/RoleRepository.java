package com.accompany.stickyrice.repository;

import com.accompany.stickyrice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("""
        SELECT r
        FROM Role r
        WHERE r.roleName = :roleName
        """)
    Role findByRoleName(@Param("roleName") String roleName);
}
