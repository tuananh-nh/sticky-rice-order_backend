package com.accompany.stickyrice.dto.response;

public class JwtDto {
    private String token;
    private String username;
    private String email;
    private String image;
    private String role;

    public JwtDto() {
    }

    public JwtDto(String token, String username, String email, String image, String role) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.image = image;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
