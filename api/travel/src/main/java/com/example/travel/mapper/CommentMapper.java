package com.example.travel.mapper;

import com.example.travel.dto.CommentResponseDto;
import com.example.travel.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper implements Mapper<Comment, CommentResponseDto> {
    @Override
    public CommentResponseDto toDto(Comment entity) {
        return new CommentResponseDto(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getContent(),
                entity.getUser().getUsername()
        );
    }
}
