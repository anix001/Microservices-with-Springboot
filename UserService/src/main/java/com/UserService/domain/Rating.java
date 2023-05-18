package com.UserService.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private UUID ratingId;
    private UUID userId;
    private UUID hotelId;
    private int rating;
    private String feedback;
    private Hotel hotel;
}
