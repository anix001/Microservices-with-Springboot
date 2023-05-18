package com.RatingService.resource;

import com.RatingService.domain.Rating;
import com.RatingService.dto.RatingDto;
import com.RatingService.service.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingResource {
    private final RatingService ratingService;

    public RatingResource(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    private final String successMessage = "Data Fetched SuccessFully";

    @PostMapping
    public ResponseEntity<RatingDto> store(@RequestBody Rating rating){
//        return new ApiResponse<>(true, successMessage, HttpStatus.CREATED, );
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.store(rating));
    }

    @GetMapping
    public ResponseEntity<List<RatingDto>> getAllRatings(){
//        return new ApiResponse<>(true, successMessage, HttpStatus.OK, );
        return  ResponseEntity.ok(ratingService.getAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingDto>> getRatingByUserId(@PathVariable UUID userId){
//        return new ApiResponse<List<RatingDto>>(true, successMessage, HttpStatus.OK, );
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RatingDto>> getRatingByHotelId(@PathVariable UUID hotelId){
//        return new ApiResponse<List<RatingDto>>(true, successMessage, HttpStatus.OK, );
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }

}
