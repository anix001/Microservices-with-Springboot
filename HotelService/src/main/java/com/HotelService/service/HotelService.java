package com.HotelService.service;

import com.HotelService.domain.Hotel;
import com.HotelService.dto.HotelDto;

import java.util.List;
import java.util.UUID;

public interface HotelService {
    HotelDto store(Hotel hotel);
    List<HotelDto> getAll();
    HotelDto get(UUID hotelId);
}
