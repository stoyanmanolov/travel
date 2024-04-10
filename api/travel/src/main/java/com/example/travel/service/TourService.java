package com.example.travel.service;

import com.example.travel.dto.TourCreationRequestDto;
import com.example.travel.exception.NotFoundException;
import com.example.travel.exception.RequestException;
import com.example.travel.model.*;
import com.example.travel.repository.BookingRepository;
import com.example.travel.repository.ReviewRepository;
import com.example.travel.repository.TourRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TourService {
    private final TourRepository tourRepository;
    private final BookingRepository bookingRepository;
    private final ImageService imageService;
    private final DestinationService destinationService;
    private final ReviewRepository reviewRepository;

    public TourService(TourRepository tourRepository, BookingRepository bookingRepository, ImageService imageService, DestinationService destinationService, ReviewRepository reviewRepository) {
        this.tourRepository = tourRepository;
        this.bookingRepository = bookingRepository;
        this.imageService = imageService;
        this.destinationService = destinationService;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Tour createTour(TourCreationRequestDto requestDto) throws IOException {
        Destination destination;

        destination = destinationService.getDestinationByName(requestDto.getDestinationName());

        if (destination == null) {
            destination = destinationService.createDestination(requestDto.getDestinationName(), requestDto.getCountry());
        }

        Tour tour = new Tour(
                requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getPrice(),
                requestDto.getStartDate(),
                requestDto.getEndDate(),
                destination
        );

        Image image = imageService.upload(requestDto.getImageFile(), ImageType.THUMBNAIL);
        tour.setThumbnailImage(image);

        tourRepository.save(tour);

        return tour;
    }

    public Tour makeFeatured(Long id) throws NotFoundException {
        Tour tour = tourRepository.findById(id).orElse(null);

        if (tour == null) {
            throw new NotFoundException("Tour not found");
        }

        tour.setFeaturedDate(LocalDateTime.now());

        tourRepository.save(tour);

        return tour;
    }

    public Tour getFeatured() {
        List<Tour> toursList = tourRepository.findAll();
        toursList.sort(Comparator.comparing(Tour::getFeaturedDate));

        Tour tour = null;

        if (!toursList.isEmpty()) {
            if (toursList.getFirst().getFeaturedDate() != null) {
                tour = toursList.getFirst();
            }
        }

        return tour;
    }

    public void deleteTour(Long id) {
        tourRepository.deleteById(id);
    }

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    public Tour getTour(Long id) {
        return tourRepository
                .findById(id)
                .orElse(null);
    }

    public void bookTour(Long tourId, User user, String phoneNumber) throws NotFoundException, RequestException {
        Tour tour = tourRepository
                .findById(tourId)
                .orElseThrow(() -> new NotFoundException("Tour with specified id is not found"));

        if (tour.getStatus() != TourStatus.UPCOMING) {
            throw new RequestException("Tour has already started");
        }

        boolean hasBooked = tour
                .getBookings()
                .stream()
                .anyMatch(b -> Objects.equals(b.getUser().getId(), user.getId()));

        if (hasBooked) {
            throw new RequestException("Tour has already been booked");
        }

        Booking booking = new Booking(phoneNumber, user, tour);

        bookingRepository.save(booking);
    }

    public Review addReview(Long tourId, int rating, String description, User user) throws NotFoundException {
        Tour tour = tourRepository
                .findById(tourId)
                .orElseThrow(() -> new NotFoundException("Tour with specified id is not found"));
        Review review = new Review(rating, description, tour, user);

        reviewRepository.save(review);

        return review;
    }

    public Set<Review> getReviews(Long tourId) throws RequestException {
        return reviewRepository
                .findAllByTourId(tourId);
    }

    public Set<Booking> getUserBookings(User user) {
        return bookingRepository
                .findAllByUserId(user.getId());
    }

    public Set<Booking> getAllBookings() {
        return new HashSet<>(
                bookingRepository.findAll()
        );
    }

    public Booking markBookingAsPaid(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow();

        booking.setPaid(true);

        return bookingRepository.save(booking);
    }

    public List<Tour> searchTours(String query) {
        return tourRepository.searchTours(query);
    }
}
