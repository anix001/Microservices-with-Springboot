package com.UserService.service;

import com.UserService.domain.User;
import com.UserService.dto.PasswordDto;
import com.UserService.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void store(User user);
    List<UserDto> getAll();
    UserDto get(UUID userId);
    UserDetails getCurrentLoggedInUser();
    void changePassword(PasswordDto passwordDto);
}
