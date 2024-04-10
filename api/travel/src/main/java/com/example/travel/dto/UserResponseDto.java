package com.example.travel.dto;

import com.example.travel.model.UserRole;

public class UserResponseDto {
    private final Long id;
    private final String username;
    private final UserRole userRole;

    public UserResponseDto(Long id, String username, UserRole userRole) {
        this.id = id;
        this.username = username;
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public UserRole getUserRole() {
        return userRole;
    }
}
