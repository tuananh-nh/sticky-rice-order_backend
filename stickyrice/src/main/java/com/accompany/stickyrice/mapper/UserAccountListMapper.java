package com.accompany.stickyrice.mapper;

import com.accompany.stickyrice.dto.response.UserAccountListDto;
import com.accompany.stickyrice.dto.request.EditUserDto;
import com.accompany.stickyrice.entity.Role;
import com.accompany.stickyrice.entity.UserAccount;

public class UserAccountListMapper {

    // Dùng để hiển thị danh sách người dùng
    public static UserAccountListDto toDto(UserAccount userAccount) {
        return new UserAccountListDto(
                userAccount.getUserId(),
                userAccount.getUsername(),
                userAccount.getImage(),
                userAccount.getEmail(),
                userAccount.getAddress(),
                userAccount.getRole().getRoleId(),
                userAccount.getRole().getRoleName()
        );
    }

    // Dùng để hiển thị dữ liệu người dùng khi sửa (edit form)
    public static EditUserDto toEditDto(UserAccount userAccount) {
        EditUserDto dto = new EditUserDto();
        dto.setUserId(userAccount.getUserId());
        dto.setFullName(userAccount.getUsername());
        dto.setEmail(userAccount.getEmail());
        dto.setPhoneNumber(userAccount.getPhoneNumber());
        dto.setAddress(userAccount.getAddress());
        dto.setImage(userAccount.getImage());
        dto.setRoleId(userAccount.getRole().getRoleId());
        // Không set password để đảm bảo bảo mật (trừ khi bạn cần)
        return dto;
    }

    // Cập nhật thông tin từ EditUserDto vào UserAccount
    public static void updateFromEditDto(UserAccount user, EditUserDto dto, Role role) {
        if (dto.getFullName() != null && !dto.getFullName().isBlank()) {
            user.setUsername(dto.getFullName());
        }

        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().isBlank()) {
            user.setPhoneNumber(dto.getPhoneNumber());
        }

        if (dto.getAddress() != null && !dto.getAddress().isBlank()) {
            user.setAddress(dto.getAddress());
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(dto.getPassword());
        }

        if (dto.getImage() != null && !dto.getImage().isBlank()) {
            user.setImage(dto.getImage());
        }

        if (role != null) {
            user.setRole(role);
        }
    }
}
