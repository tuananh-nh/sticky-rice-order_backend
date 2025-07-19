package com.accompany.stickyrice.service;

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
}
