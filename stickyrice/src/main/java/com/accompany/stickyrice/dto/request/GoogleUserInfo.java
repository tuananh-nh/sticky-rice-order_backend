package com.accompany.stickyrice.dto.request;

public class GoogleUserInfo {
    private String sub;
    private String username;
    private String email;
    private String image;

    public GoogleUserInfo() {
    }

    public GoogleUserInfo(String sub, String username, String email, String image) {
        this.sub = sub;
        this.username = username;
        this.email = email;
        this.image = image;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
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
}
