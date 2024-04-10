package com.example.travel.controller;

import com.example.travel.dto.UserResponseDto;
import com.example.travel.mapper.UserMapper;
import com.example.travel.model.User;
import com.example.travel.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;
    private final UserMapper userMapper;

    public AdminController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PatchMapping("authorize/{id}")
    public ResponseEntity<UserResponseDto> makeAnAdmin(@PathVariable Long id) {
        User user = userService.authorize(id);

        return ResponseEntity.ok(
                userMapper.toDto(user)
        );
    }
}
