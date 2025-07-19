package com.accompany.stickyrice.service.impl;

import com.accompany.stickyrice.dto.request.EditUserDto;
import com.accompany.stickyrice.dto.response.UserAccountListDto;
import com.accompany.stickyrice.entity.Role;
import com.accompany.stickyrice.entity.UserAccount;
import com.accompany.stickyrice.exception.ResourceNotFoundException;
import com.accompany.stickyrice.mapper.UserAccountListMapper;
import com.accompany.stickyrice.repository.RoleRepository;
import com.accompany.stickyrice.repository.UserAccountRepository;
import com.accompany.stickyrice.service.UserAccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userRepository;
    private final RoleRepository roleRepository;

    public UserAccountServiceImpl(UserAccountRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserAccount create(UserAccount user) {
        return userRepository.save(user);
    }

    @Override
    public UserAccount update(UUID uuid, UserAccount updatedUser) {
        return userRepository.findById(uuid).map(existingUser -> {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setImage(updatedUser.getImage());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setRole(updatedUser.getRole());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new NoSuchElementException("Không tìm thấy người dùng"));
    }

    @Override
    public Optional<UserAccount> findById(UUID uuid) {
        return userRepository.findById(uuid);
    }

    @Override
    public List<UserAccount> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(UUID uuid) {
        if (!userRepository.existsById(uuid)) {
            throw new NoSuchElementException("Người dùng không tồn tại");
        }
        userRepository.deleteById(uuid);
    }

    @Override
    public Boolean existsById(UUID uuid) {
        return userRepository.existsById(uuid);
    }

    @Override
    public List<UserAccountListDto> getUserAccountList() {
        Pageable pageable = PageRequest.of(0, 5);
        return userRepository.findAllUserAccount(pageable)
                .stream()
                .map(UserAccountListMapper::toDto)
                .toList();
    }

    @Override
    public Map<String, Object> getPaginatedUser_Account(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserAccount> userAccountPage = userRepository.findAllUserAccount(pageable);

        List<UserAccountListDto> dtos = userAccountPage.getContent()
                .stream()
                .map(UserAccountListMapper::toDto)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("data", dtos);
        response.put("currentPage", userAccountPage.getNumber() + 1); // vì client đếm từ 1
        response.put("totalItems", userAccountPage.getTotalElements());
        response.put("totalPages", userAccountPage.getTotalPages());

        return response;
    }

    // ✅ Hàm update từ EditUserDto
    @Override
    public void updateUser(EditUserDto dto) {
        UserAccount user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng"));

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy vai trò"));

        UserAccountListMapper.updateFromEditDto(user, dto, role);

        userRepository.save(user);
    }
}
