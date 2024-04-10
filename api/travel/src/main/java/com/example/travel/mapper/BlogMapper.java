package com.example.travel.mapper;

import com.example.travel.dto.BlogResponseDto;
import com.example.travel.model.Blog;
import com.example.travel.model.Tag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogMapper implements Mapper<Blog, BlogResponseDto> {
    @Override
    public BlogResponseDto toDto(Blog entity) {
        Long thumbnailImageId = entity.getThumbnailImage() != null
                ? entity.getThumbnailImage().getId()
                : null;
        List<String> tagNames = entity.getTags() != null
                ? entity.getTags().stream().map(Tag::getName).toList()
                : null;

        return new BlogResponseDto(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getTitle(),
                entity.getSubtitle(),
                entity.getContent(),
                thumbnailImageId,
                entity.getUser().getUsername(),
                entity.getTour().getTitle(),
                tagNames
        );
    }
}
