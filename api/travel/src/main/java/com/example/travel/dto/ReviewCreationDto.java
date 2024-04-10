package com.example.travel.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ReviewCreationDto {
    @NotNull(message = "Rating should be provided")
    @Min(value = 1, message = "Rating should be 1 or more")
    @Max(value = 5, message = "Rating should be 5 or less")
    private final int rating;

    private final String description;

    public ReviewCreationDto(int rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }
}
