package com.HotelService.resource;

import com.HotelService.domain.Hotel;
import com.HotelService.dto.HotelDto;
import com.HotelService.exception.ApiResponse;
import com.HotelService.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelResource {
    private final HotelService hotelService;

    public HotelResource(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    private final String successMessage = "Data Fetched Successfully";

    @PostMapping
    public ResponseEntity<HotelDto> store(@RequestBody Hotel hotel){
//        return new ApiResponse<HotelDto>(true, successMessage,HttpStatus.CREATED, );
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.store(hotel));
    }


    @GetMapping
    public ResponseEntity<List<HotelDto>> getAll(){
//        return new ApiResponse<List<HotelDto>>(true, successMessage, HttpStatus.OK, );
        return ResponseEntity.ok(hotelService.getAll());
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> get(@PathVariable UUID hotelId){
//        return new ApiResponse<>(true, successMessage, HttpStatus.OK, );
        return ResponseEntity.ok(hotelService.get(hotelId));
    }

}
