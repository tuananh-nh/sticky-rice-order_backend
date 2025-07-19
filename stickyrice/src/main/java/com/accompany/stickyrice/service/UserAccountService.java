package com.accompany.stickyrice.service;

import com.accompany.stickyrice.entity.UserAccount;

import java.util.Optional;

public interface UserAccountService {
    Optional<UserAccount> findByEmail (String email);
    UserAccount create (UserAccount userAccount);
}
