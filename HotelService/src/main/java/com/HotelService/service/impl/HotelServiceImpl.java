package com.HotelService.service.impl;

import com.HotelService.domain.Hotel;
import com.HotelService.dto.HotelDto;
import com.HotelService.exception.ResourceNotFoundException;
import com.HotelService.repository.HotelRepository;
import com.HotelService.service.HotelService;
import com.HotelService.service.mapper.HotelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelServiceImpl(HotelRepository hotelRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    @Override
    public HotelDto store(Hotel hotel) {
        UUID hotelId = UUID.randomUUID();
        hotel.setId(hotelId);
        hotelRepository.save(hotel);
        return hotelMapper.modelTODto(hotel);
    }

    @Override
    public List<HotelDto> getAll() {
        return hotelMapper.modelToDtos(hotelRepository.findAll());
    }

    @Override
    public HotelDto get(UUID hotelId) {
        Hotel searchedHotel = hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel is not found with id: "+ hotelId));
        return hotelMapper.modelTODto(searchedHotel);
    }
}
