package com.accompany.stickyrice.service;

<<<<<<< HEAD
import com.accompany.stickyrice.dto.response.UserAccountListDto;
import com.accompany.stickyrice.dto.request.EditUserDto;
import com.accompany.stickyrice.entity.UserAccount;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserAccountService extends BaseService<UserAccount, UUID> {

    List<UserAccountListDto> getUserAccountList();

    Map<String, Object> getPaginatedUser_Account(int page, int size);

    void updateUser(EditUserDto dto); //  Thêm phương thức update
=======
import com.accompany.stickyrice.entity.UserAccount;

import java.util.Optional;

public interface UserAccountService {
    Optional<UserAccount> findByEmail (String email);
    UserAccount create (UserAccount userAccount);
>>>>>>> 7fbcc85325be2b453674959b659dff9ac81a85c0
}
