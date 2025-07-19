package com.accompany.stickyrice.repository;

import com.accompany.stickyrice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

public interface RoleRepository extends JpaRepository<Role, Integer> {

    // ✅ Có thể thêm phương thức tùy chỉnh nếu cần, ví dụ:

    // Tìm role theo tên
    Role findByRoleName(String roleName);

    // Kiểm tra role đã tồn tại theo tên
    boolean existsByRoleName(String roleName);
=======
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("""
        SELECT r
        FROM Role r
        WHERE r.roleName = :roleName
        """)
    Role findByRoleName(@Param("roleName") String roleName);
>>>>>>> 7fbcc85325be2b453674959b659dff9ac81a85c0
}
