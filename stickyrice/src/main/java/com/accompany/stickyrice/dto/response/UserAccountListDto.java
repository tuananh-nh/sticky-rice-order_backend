package com.accompany.stickyrice.dto.response;

import java.util.UUID;

public class UserAccountListDto {

    private UUID userId;
    private String username;
    private String image;
    private String email;
    private Integer roleId;
    private String roleName;
    private String address;

    public UserAccountListDto() {
    }

    public UserAccountListDto(UUID userId, String username, String image, String email,
                              String address, Integer roleId, String roleName) {
        this.userId = userId;
        this.username = username;
        this.image = image;
        this.email = email;
        this.address = address;
        this.roleId = roleId;
        this.roleName = roleName;
    }


    // Getters and Setters
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
