package com.example.travel.dto;

public class BookingResponseDto {
    private final Long id;
    private final String phoneNumber;
    private final boolean paid;
    private final Long tourId;
    private final String tourTitle;
    private final Long userId;
    private final String username;

    public BookingResponseDto(Long id, String phoneNumber, boolean paid, Long tourId, String tourTitle, Long userId, String username) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.paid = paid;
        this.tourId = tourId;
        this.tourTitle = tourTitle;
        this.userId = userId;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public Long getTourId() {
        return tourId;
    }

    public String getTourTitle() {
        return tourTitle;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public boolean isPaid() {
        return paid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
