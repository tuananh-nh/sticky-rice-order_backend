package com.accompany.stickyrice.service.impl;

import com.accompany.stickyrice.dto.request.GoogleUserInfo;
import com.accompany.stickyrice.dto.response.JwtDto;
import com.accompany.stickyrice.entity.Role;
import com.accompany.stickyrice.entity.UserAccount;
import com.accompany.stickyrice.security.JwtTokenProvider;
import com.accompany.stickyrice.service.AuthService;
import com.accompany.stickyrice.service.RoleService;
import com.accompany.stickyrice.service.UserAccountService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AuthServiceImpl implements AuthService {
    private final UserAccountService userAccountService;

    private final RoleService roleService;

    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(UserAccountService userAccountService, RoleService roleService, JwtTokenProvider jwtTokenProvider) {
        this.userAccountService = userAccountService;
        this.roleService = roleService;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    public JwtDto authenticateWithGoogle(String accessToken, boolean isAdmin) {

        // call Google api get info user
        GoogleUserInfo googleUserInfo = fetchGoogleUserInfo(accessToken);

        // user is existed ?
        UserAccount userAccount = userAccountService.findByEmail(googleUserInfo.getEmail())
                .orElseGet(() -> {
                    UserAccount newUser = new UserAccount();
                    newUser.setUsername(googleUserInfo.getUsername());
                    newUser.setEmail(googleUserInfo.getEmail());
                    newUser.setImage(googleUserInfo.getImage());
                    Role role = roleService.findByRoleName(isAdmin ? "Admin" : "Customer");
                    newUser.setRole(role);
                    return userAccountService.create(newUser);
                });

        // create token
        String token = jwtTokenProvider.generateToken(userAccount);
        return new JwtDto(token, userAccount.getUsername(), userAccount.getEmail(), userAccount.getImage(), userAccount.getRole().getRoleName());

    }

    private GoogleUserInfo fetchGoogleUserInfo (String accessToken) {
        // to call api http -- get -- post -- put
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);


        ResponseEntity<GoogleUserInfo> response = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v3/userinfo",
                HttpMethod.GET,
                entity,
                GoogleUserInfo.class // json is map with GoogleUserInfo
        );

        return response.getBody();


    }
}
