package com.RatingService.service;

import com.RatingService.domain.Rating;
import com.RatingService.dto.RatingDto;

import java.util.List;
import java.util.UUID;

public interface RatingService {

    RatingDto store(Rating rating);
    List<RatingDto> getAll();
    List<RatingDto> getRatingByUserId(UUID userId);
    List<RatingDto> getRatingByHotelId(UUID hotelId);
}
