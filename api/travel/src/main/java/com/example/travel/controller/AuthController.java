package com.example.travel.controller;

import com.example.travel.dto.AuthRequestDto;
import com.example.travel.dto.AuthResponseDto;
import com.example.travel.exception.ConflictException;
import com.example.travel.exception.RequestException;
import com.example.travel.model.User;
import com.example.travel.service.AuthService;
import com.example.travel.service.JwtTokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtTokenService jwtTokenService;

    public AuthController(AuthService authService, JwtTokenService jwtTokenService) {
        this.authService = authService;
        this.jwtTokenService = jwtTokenService;
    }

    @GetMapping("/user")
    public ResponseEntity<AuthResponseDto> getAuthenticatedUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        AuthResponseDto responseDto = new AuthResponseDto(user.getUsername(), user.getUserRole());

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto requestDto) throws RequestException {
        User user = authService.login(requestDto.getUsername(), requestDto.getPassword());
        AuthResponseDto responseDto = new AuthResponseDto(user.getUsername(), user.getUserRole());

        String token = jwtTokenService.generateToken(user);
        responseDto.setToken(token);

        return ResponseEntity.ok(responseDto);
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody AuthRequestDto requestDto) throws ConflictException {
        User user = authService.register(requestDto.getUsername(), requestDto.getPassword());
        AuthResponseDto responseDto = new AuthResponseDto(user.getUsername(), user.getUserRole());

        String token = jwtTokenService.generateToken(user);
        responseDto.setToken(token);

        return ResponseEntity.ok(responseDto);
    }
}
