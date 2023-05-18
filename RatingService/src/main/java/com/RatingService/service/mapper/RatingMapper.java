package com.RatingService.service.mapper;

import com.RatingService.domain.Rating;
import com.RatingService.dto.RatingDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    RatingDto modelToDto(Rating rating);

    List<RatingDto> modelTODtos(List<Rating> ratings);
}
