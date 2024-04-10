package com.example.travel.dto;

import com.example.travel.model.TourStatus;

import java.math.BigDecimal;

public record TourResponseDto(
        Long id,
        String title,
        String description,
        BigDecimal price,
        String startDate,
        String endDate,
        TourStatus status,
        Long thumbnailImageId,
        Long destinationId,
        String destinationName,
        String destinationCountry,
        Integer reviewsCount,
        Float reviewsRatingAverage
) {
}
