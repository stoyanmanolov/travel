package com.example.travel.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class BlogCreationDto {
    @NotEmpty(message = "Empty title")
    private final String title;

    @NotEmpty(message = "Empty subtitle")
    private final String subtitle;

    @NotEmpty(message = "Empty content")
    private final String content;

    @NotNull(message = "Image not provided")
    private final MultipartFile imageFile;

    @NotNull(message = "Tour not provided")
    private final Long tourId;

    public BlogCreationDto(String title, String subtitle, String content, MultipartFile imageFile, Long tourId) {
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.imageFile = imageFile;
        this.tourId = tourId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getContent() {
        return content;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public Long getTourId() {
        return tourId;
    }
}
