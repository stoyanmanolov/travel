package com.example.travel.repository;

import com.example.travel.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    @Query("SELECT t FROM Tour t WHERE " +
            "t.title LIKE CONCAT('%',:query, '%')")
    List<Tour> searchTours(String query);
}
