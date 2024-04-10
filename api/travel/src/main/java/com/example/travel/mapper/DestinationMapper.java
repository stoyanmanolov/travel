package com.example.travel.mapper;

import com.example.travel.dto.DestinationResponseDto;
import com.example.travel.model.Destination;
import org.springframework.stereotype.Component;

@Component
public class DestinationMapper implements Mapper<Destination, DestinationResponseDto> {

    @Override
    public DestinationResponseDto toDto(Destination destination) {
        return new DestinationResponseDto(
                destination.getId(),
                destination.getName(),
                destination.getCountry(),
                destination.getTours().size()
        );
    }
}
