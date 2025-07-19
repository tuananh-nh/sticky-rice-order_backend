package com.accompany.stickyrice.controller;

import com.accompany.stickyrice.dto.request.EditUserDto;
import com.accompany.stickyrice.dto.response.UserAccountListDto;
import com.accompany.stickyrice.entity.UserAccount;
import com.accompany.stickyrice.mapper.UserAccountListMapper;
import com.accompany.stickyrice.service.UserAccountService;
import com.accompany.stickyrice.exception.ResourceNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*") // nếu gọi từ frontend localhost:5173
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/users/top5")
    public ResponseEntity<List<UserAccountListDto>> getTop5Users() {
        List<UserAccountListDto> users = userAccountService.getUserAccountList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Map<String, Object> result = userAccountService.getPaginatedUser_Account(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/users/edit/{id}")
    public ResponseEntity<EditUserDto> getUserById(@PathVariable UUID id) {
        UserAccount user = userAccountService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng"));

        EditUserDto dto = UserAccountListMapper.toEditDto(user); // ✅ đúng hàm
        return ResponseEntity.ok(dto);
    }



    @PutMapping("/users/edit/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable UUID id, @RequestBody EditUserDto dto) {
        dto.setUserId(id);
        userAccountService.updateUser(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userAccountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
