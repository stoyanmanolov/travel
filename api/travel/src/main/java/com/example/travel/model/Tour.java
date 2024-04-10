package com.example.travel.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tour")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description", nullable = false, length = 10000)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "featured_date", nullable = true)
    private LocalDateTime featuredDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image thumbnailImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", referencedColumnName = "id", nullable = false)
    private Destination destination;

    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Booking> bookings;

    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Review> reviews;

    @Transient
    private TourStatus status;

    @Transient
    private Float reviewsRatingAverage;

    public Tour() {
    }

    public Tour(String title, String description, BigDecimal price, LocalDateTime startDate, LocalDateTime endDate, Destination destination) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Image getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(Image thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public TourStatus getStatus() {
        LocalDateTime currentDate = LocalDateTime.now();

        if (currentDate.isBefore(startDate)) {
            return TourStatus.UPCOMING;
        } else if (currentDate.isAfter(startDate) && currentDate.isBefore(endDate)) {
            return TourStatus.ACTIVE;
        } else {
            return TourStatus.FINISHED;
        }
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public LocalDateTime getFeaturedDate() {
        return featuredDate;
    }

    public void setFeaturedDate(LocalDateTime featuredDate) {
        this.featuredDate = featuredDate;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Float getReviewsRatingAverage() {
        if (reviews != null) {
            float sum = reviews.stream().map(Review::getRating).reduce(0, Integer::sum);
            float size = reviews.size();

            if (sum != 0 && size != 0) {
                return sum / size;
            }
        }

        return 0.0F;
    }
}
