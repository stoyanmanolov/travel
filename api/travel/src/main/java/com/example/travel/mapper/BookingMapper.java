package com.example.travel.mapper;

import com.example.travel.dto.BookingResponseDto;
import com.example.travel.model.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper implements Mapper<Booking, BookingResponseDto> {
    @Override
    public BookingResponseDto toDto(Booking entity) {
        return new BookingResponseDto(
                entity.getId(),
                entity.getPhoneNumber(),
                entity.getPaid(),
                entity.getTour().getId(),
                entity.getTour().getTitle(),
                entity.getUser().getId(),
                entity.getUser().getUsername()
        );
    }
}
