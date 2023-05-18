package com.HotelService.service.mapper;

import com.HotelService.domain.Hotel;
import com.HotelService.dto.HotelDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelDto modelTODto(Hotel hotel);

    List<HotelDto> modelToDtos(List<Hotel> hotelList);
}
