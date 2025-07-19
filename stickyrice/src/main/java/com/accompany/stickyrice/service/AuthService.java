package com.accompany.stickyrice.service;

import com.accompany.stickyrice.dto.response.JwtDto;

public interface AuthService {
    JwtDto authenticateWithGoogle(String accessToken, boolean isAdmin);
}

