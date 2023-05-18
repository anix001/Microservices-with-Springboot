package com.UserService.service.mapper;

import com.UserService.domain.User;
import com.UserService.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    @Mapping(source = "user.description", target = "about")
    UserDto modelTODto(User user);

    List<UserDto> modelTODtos(List<User> userList);
}
