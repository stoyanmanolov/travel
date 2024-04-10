package com.example.travel.service;

import com.example.travel.exception.ConflictException;
import com.example.travel.exception.RequestException;
import com.example.travel.model.User;
import com.example.travel.model.UserRole;
import com.example.travel.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenService jwtTokenService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User register(String username, String password) throws ConflictException {
        boolean alreadyExists = userRepository
                .findByUsername(username)
                .isPresent();
        if (alreadyExists) {
            throw new ConflictException("Username already exists");
        }
        User user = new User(username, passwordEncoder.encode(password), UserRole.USER);
        userRepository.save(user);
        return user;
    }

    public User login(String username, String password) throws RequestException {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new RequestException("Invalid username or password"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
        return user;
    }
}
