package com.example.travel.mapper;

import com.example.travel.dto.ReviewResponseDto;
import com.example.travel.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper implements Mapper<Review, ReviewResponseDto>  {
    @Override
    public ReviewResponseDto toDto(Review review) {
        return new ReviewResponseDto(
                review.getUser().getUsername(),
                review.getRating(),
                review.getDescription()
        );
    }
}
