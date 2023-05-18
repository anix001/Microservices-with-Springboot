package com.UserService.external.service;

import com.UserService.domain.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/api/v1/hotels/{hotelId}")
    Hotel getHotel(@PathVariable UUID hotelId);
}
