package com.RatingService.service.impl;

import com.RatingService.domain.Rating;
import com.RatingService.dto.RatingDto;
import com.RatingService.repository.RatingRepository;
import com.RatingService.service.RatingService;
import com.RatingService.service.mapper.RatingMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    public RatingServiceImpl(RatingRepository ratingRepository, RatingMapper ratingMapper) {
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
    }

    @Override
    public RatingDto store(Rating rating) {
        UUID ratingId = UUID.randomUUID();
        rating.setRatingId(ratingId);
        ratingRepository.save(rating);
        return ratingMapper.modelToDto(rating);
    }

    @Override
    public List<RatingDto> getAll() {
        return ratingMapper.modelTODtos(ratingRepository.findAll());
    }

    @Override
    public List<RatingDto> getRatingByUserId(UUID userId) {
        return ratingMapper.modelTODtos(ratingRepository.findByUserId(userId));
    }

    @Override
    public List<RatingDto> getRatingByHotelId(UUID hotelId) {
        return ratingMapper.modelTODtos(ratingRepository.findByHotelId(hotelId));
    }
}
