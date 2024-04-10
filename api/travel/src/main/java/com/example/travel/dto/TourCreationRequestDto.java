package com.example.travel.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TourCreationRequestDto {
    @NotEmpty(message = "Empty title")
    private final String title;

    @NotEmpty(message = "Empty description")
    private final String description;

    @NotNull(message = "No price provided")
    @Positive(message = "The price cannot be a negative number")
    private final BigDecimal price;

    @NotNull(message = "No image file provided")
    private final MultipartFile imageFile;

    @NotEmpty(message = "Empty destination name")
    private final String destinationName;

    @NotEmpty(message = "Empty country")
    private final String country;

    @NotNull(message = "No start date specified")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private final LocalDateTime startDate;

    @NotNull(message = "No end date specified")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private final LocalDateTime endDate;

    public TourCreationRequestDto(
            String title,
            String description,
            BigDecimal price,
            MultipartFile imageFile,
            String destinationName,
            String country,
            LocalDateTime startDate,
            LocalDateTime endDate
    ) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageFile = imageFile;
        this.destinationName = destinationName;
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public String getCountry() {
        return country;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
