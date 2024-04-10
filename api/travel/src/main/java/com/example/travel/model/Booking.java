package com.example.travel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "booking", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "tour_id" }))
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "paid", nullable = false, columnDefinition = "boolean default false")
    private Boolean paid = false;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_id", referencedColumnName = "id", nullable = false)
    private Tour tour;

    public Booking() {
    }

    public Booking(String phoneNumber, User user, Tour tour) {
        this.phoneNumber = phoneNumber;
        this.user = user;
        this.tour = tour;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public User getUser() {
        return user;
    }

    public Tour getTour() {
        return tour;
    }

    public Long getId() {
        return id;
    }
}
