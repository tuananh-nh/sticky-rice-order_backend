package com.accompany.stickyrice.repository;

import com.accompany.stickyrice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    // ✅ Có thể thêm phương thức tùy chỉnh nếu cần, ví dụ:

    // Tìm role theo tên
    Role findByRoleName(String roleName);

    // Kiểm tra role đã tồn tại theo tên
    boolean existsByRoleName(String roleName);
}
