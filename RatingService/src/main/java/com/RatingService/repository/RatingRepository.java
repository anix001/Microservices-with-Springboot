package com.RatingService.repository;

import com.RatingService.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID> {

    List<Rating> findByUserId(UUID userId);
    List<Rating>findByHotelId(UUID hotelId);
}
