package com.RatingService.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_ratings")
public class Rating {
    @Id
    private UUID ratingId;
    private UUID userId;
    private UUID hotelId;
    private int rating;
    private String feedback;
}
