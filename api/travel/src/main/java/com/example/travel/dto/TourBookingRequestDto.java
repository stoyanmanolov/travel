package com.example.travel.dto;

import jakarta.validation.constraints.NotEmpty;

public class TourBookingRequestDto {
    @NotEmpty(message = "Empty phone number")
    private String phoneNumber;

    public TourBookingRequestDto() {
    }

    public TourBookingRequestDto(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
