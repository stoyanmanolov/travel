package com.example.travel.mapper;

import com.example.travel.dto.UserResponseDto;
import com.example.travel.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserResponseDto> {
    @Override
    public UserResponseDto toDto(User entity) {
        return new UserResponseDto(
                entity.getId(),
                entity.getUsername(),
                entity.getUserRole()
        );
    }
}
