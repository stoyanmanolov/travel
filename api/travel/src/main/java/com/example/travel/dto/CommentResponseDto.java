package com.example.travel.dto;

import java.time.LocalDateTime;

public record CommentResponseDto(
   Long id,
   LocalDateTime createdAt,
   String content,
   String username
) {}
