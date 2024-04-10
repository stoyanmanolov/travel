package com.example.travel.dto;

import com.example.travel.model.UserRole;

public class AuthResponseDto {
    private final String username;
    private final UserRole userRole;
    private String token;

    public AuthResponseDto(String username, UserRole userRole) {
        this.username = username;
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
