package com.example.travel.dto;

public class ReviewResponseDto {
    private final String username;
    private final int rating;
    private final String description;

    public ReviewResponseDto(String username, int rating, String description) {
        this.username = username;
        this.rating = rating;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }
}
