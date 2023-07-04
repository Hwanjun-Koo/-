package com.example.football_community.controller;

import com.example.football_community.dto.UserDTO;
import com.example.football_community.entity.User;
import com.example.football_community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/football-community/user")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(
            @PathVariable Long userId) {
        UserDTO userDTO = userService.getUser(userId);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOList = userService.getAllUser();
        return ResponseEntity.ok(userDTOList);
    }

    @PutMapping("/modification/{userId}")
    public ResponseEntity<String> updateUser(
            @PathVariable Long userId,
            @RequestBody UserDTO userDTO
    ) {
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        String message = updatedUser.getUsername() + "님의 정보가 수정되었습니다.";
        return ResponseEntity.ok(message + "\n" + updatedUser.toString());
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long userId
    ) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("회원탈퇴가 완료되었습니다.");
    }

}
