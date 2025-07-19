package com.accompany.stickyrice.service.impl;

import com.accompany.stickyrice.entity.UserAccount;
import com.accompany.stickyrice.repository.UserAccountRepository;
import com.accompany.stickyrice.service.UserAccountService;

import java.util.Optional;

public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public Optional<UserAccount> findByEmail(String email) {
        return userAccountRepository.findByEmail(email);
    }

    @Override
    public UserAccount create(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }
}
