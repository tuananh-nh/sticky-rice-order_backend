package com.accompany.stickyrice.dto.request;

public class GoogleTokenDto {
    private String accessToken;

    public GoogleTokenDto() {
    }

    public GoogleTokenDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
