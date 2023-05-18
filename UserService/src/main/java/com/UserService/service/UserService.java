package com.UserService.service;

import com.UserService.domain.User;
import com.UserService.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto store(User user);
    List<UserDto> getAll();
    UserDto get(UUID userId);
}
