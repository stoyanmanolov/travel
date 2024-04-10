package com.example.travel.repository;

import com.example.travel.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query("SELECT t FROM Tag t JOIN t.blogs b WHERE b.id = ?1 AND t.name = ?2")
    Optional<Tag> findByBlogIdAndName(Long blogId, String name);

    @Query("SELECT t FROM Tag t WHERE t.name = ?1")
    Optional<Tag> findByName(String name);
}
