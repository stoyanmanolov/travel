package com.example.travel.dto;

public class DestinationResponseDto {
    private final Long id;
    private final String name;
    private final String country;
    private final Integer toursCount;

    public DestinationResponseDto(Long id, String name, String country, Integer toursCount) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.toursCount = toursCount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public Integer getToursCount() {
        return toursCount;
    }
}
