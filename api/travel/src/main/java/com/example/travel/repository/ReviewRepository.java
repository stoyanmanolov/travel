package com.example.travel.repository;

import com.example.travel.model.Review;
import com.example.travel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.tour.id = ?1")
    Set<Review> findAllByTourId(Long tourId);
}
