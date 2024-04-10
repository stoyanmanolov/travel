package com.example.travel.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AuthRequestDto {
    @NotEmpty(message = "Empty username")
    @Size(min = 6, message = "The username should be more than 6 characters")
    private final String username;

    @NotEmpty(message = "Empty password")
    @Size(min = 6, message = "The password should be more than 6 characters")
    private final String password;

    public AuthRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
