package com.UserService.resource;

import com.UserService.domain.User;
import com.UserService.dto.ApiResponse;
import com.UserService.dto.UserDto;
import com.UserService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    private final String successMessage = "Data Fetched Successfully";

    @PostMapping
    public ApiResponse<UserDto> store(@RequestBody User user){
        return new ApiResponse<UserDto>(true, successMessage, HttpStatus.CREATED, userService.store(user));
    }

    @GetMapping
    public ApiResponse<List<UserDto>> getAll(){
        return new ApiResponse<>(true, successMessage, HttpStatus.OK, userService.getAll());
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserDto> get(@PathVariable UUID userId){
        return new ApiResponse<UserDto>(true, successMessage, HttpStatus.OK, userService.get(userId));
    }
}
