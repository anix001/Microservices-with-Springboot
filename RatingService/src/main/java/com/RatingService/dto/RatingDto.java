package com.RatingService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {
    @Id
    private UUID ratingId;
    private UUID userId;
    private UUID hotelId;
    private int rating;
    private String feedback;
}
