package com.example.travel.mapper;

import com.example.travel.dto.TourResponseDto;
import com.example.travel.model.Tour;
import org.springframework.stereotype.Component;

@Component
public class TourMapper implements Mapper<Tour, TourResponseDto> {
    @Override
    public TourResponseDto toDto(Tour tour) {
        Long thumbnailImageId = tour.getThumbnailImage() != null ? tour.getThumbnailImage().getId() : null;
        int size = tour.getReviews() != null ? tour.getReviews().size() : 0;

        return new TourResponseDto(
                tour.getId(),
                tour.getTitle(),
                tour.getDescription(),
                tour.getPrice(),
                tour.getStartDate().toString(),
                tour.getEndDate().toString(),
                tour.getStatus(),
                thumbnailImageId,
                tour.getDestination().getId(),
                tour.getDestination().getName(),
                tour.getDestination().getCountry(),
                size,
                tour.getReviewsRatingAverage()
        );
    }
}
