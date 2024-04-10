package com.example.travel.dto;

import jakarta.validation.constraints.NotEmpty;

public class CommentCreationDto {
    @NotEmpty(message = "Empty content")
    private String content;

    public CommentCreationDto() {
    }

    public CommentCreationDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}