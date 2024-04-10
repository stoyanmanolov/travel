package com.example.travel.dto;

import java.time.LocalDateTime;
import java.util.List;

public record BlogResponseDto (
        Long id,
        LocalDateTime createdAt,
        String title,
        String subtitle,
        String content,
        Long thumbnailImageId,
        String username,
        String tourTitle,
        List<String> tagNames
) {

}
