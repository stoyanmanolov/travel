package com.example.travel.service;

import com.example.travel.model.User;
import com.example.travel.model.UserRole;
import com.example.travel.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Wrong username or password"));
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User authorize(Long id) {
        User user = this.getUser(id);

        user.setUserRole(UserRole.ADMIN);

        return userRepository.save(user);
    }
}
