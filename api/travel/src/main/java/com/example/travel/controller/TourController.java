package com.example.travel.controller;

import com.example.travel.dto.*;
import com.example.travel.exception.NotFoundException;
import com.example.travel.exception.RequestException;
import com.example.travel.mapper.BookingMapper;
import com.example.travel.mapper.ReviewMapper;
import com.example.travel.mapper.TourMapper;
import com.example.travel.model.Booking;
import com.example.travel.model.Review;
import com.example.travel.model.Tour;
import com.example.travel.model.User;
import com.example.travel.service.TourService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/tours")
public class TourController {
    private final TourService tourService;
    private final TourMapper tourMapper;
    private final ReviewMapper reviewMapper;
    private final BookingMapper bookingMapper;

    public TourController(TourService tourService, TourMapper tourMapper, ReviewMapper reviewMapper, BookingMapper bookingMapper) {
        this.tourService = tourService;
        this.tourMapper = tourMapper;
        this.reviewMapper = reviewMapper;
        this.bookingMapper = bookingMapper;
    }

    @PostMapping
    public ResponseEntity<TourResponseDto> createTour(@Valid @ModelAttribute TourCreationRequestDto requestDto) throws IOException {
        Tour tour = tourService.createTour(requestDto);

        return ResponseEntity.ok(
                tourMapper.toDto(tour)
        );
    }

    @GetMapping
    public ResponseEntity<List<TourResponseDto>> getTours() {
        List<Tour> tours = tourService.getAllTours();

        return ResponseEntity.ok(
                tours
                        .stream()
                        .map(tourMapper::toDto)
                        .toList()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<TourResponseDto> getTour(@PathVariable Long id) throws NotFoundException {
        Tour tour = tourService.getTour(id);

        return ResponseEntity.ok(
                tourMapper.toDto(tour)
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTour(@PathVariable Long id) {
        tourService.deleteTour(id);

        return ResponseEntity.ok("Tour was deleted successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<TourResponseDto>> searchTours(@RequestParam String query) {
        List<Tour> tours = tourService.searchTours(query);
        
        return ResponseEntity.ok(
                tours
                        .stream()
                        .map(tourMapper::toDto)
                        .toList()
        );
    }

    @PostMapping("{id}/book")
    public ResponseEntity<String> bookTour(@PathVariable Long id,
                                           @RequestBody TourBookingRequestDto dto) throws RequestException, NotFoundException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        tourService.bookTour(id, user, dto.getPhoneNumber());

        return ResponseEntity.ok("Tour was booked successfully");
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingResponseDto>> getAllBookings() {
        Set<Booking> bookings = tourService.getAllBookings();

        return ResponseEntity.ok(
                bookings
                        .stream()
                        .map(bookingMapper::toDto)
                        .toList()
        );
    }

    @PostMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingResponseDto> getAllBookings(@PathVariable Long bookingId) {
        Booking booking = tourService.markBookingAsPaid(bookingId);

        return ResponseEntity.ok(
                bookingMapper.toDto(booking)
        );
    }

    @GetMapping("/user-bookings")
    public ResponseEntity<List<BookingResponseDto>> getUserBookings() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Set<Booking> bookings = tourService.getUserBookings(user);

        return ResponseEntity.ok(
                bookings
                        .stream()
                        .map(bookingMapper::toDto)
                        .toList()
        );
    }

    @GetMapping("/featured")
    public ResponseEntity<TourResponseDto> getFeatured() {
        Tour tour = tourService.getFeatured();

        if (tour == null) {
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.ok(
                tourMapper.toDto(tour)
        );
    }

    @PatchMapping("{id}/featured")
    public ResponseEntity<TourResponseDto> makeFeatured(@PathVariable Long id) throws NotFoundException {
        Tour tour = tourService.makeFeatured(id);

        return ResponseEntity.ok(
                tourMapper.toDto(tour)
        );
    }

    @GetMapping("{id}/reviews")
    public ResponseEntity<List<ReviewResponseDto>> getReviews(@PathVariable Long id) throws RequestException {
        Set<Review> reviewsList = tourService.getReviews(id);

        return ResponseEntity.ok(
                reviewsList
                        .stream()
                        .map(reviewMapper::toDto)
                        .toList()
        );
    }

    @PostMapping("{id}/review")
    public ResponseEntity<ReviewResponseDto> addReview(@PathVariable Long id, @Valid @RequestBody ReviewCreationDto dto) throws NotFoundException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Review review = tourService.addReview(id, dto.getRating(), dto.getDescription(), user);

        return ResponseEntity.ok(
                reviewMapper.toDto(review)
        );
    }
}
