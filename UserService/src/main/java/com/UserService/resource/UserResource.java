package com.UserService.resource;

import com.UserService.domain.User;
import com.UserService.dto.ApiResponse;
import com.UserService.dto.PasswordDto;
import com.UserService.dto.UserDto;
import com.UserService.enumeration.Role;
import com.UserService.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping
    @CircuitBreaker(name ="ratingHotelBreaker", fallbackMethod = "ratingHotelListFallbackMethod")
    public ApiResponse<List<UserDto>> getAll(){
        return new ApiResponse<>(true, successMessage, HttpStatus.OK, userService.getAll());
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallbackMethod")
//    @Retry(name="userRatingService", fallbackMethod = "ratingHotelFallbackMethod")
//    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallbackMethod")
    public ApiResponse<UserDto> get(@PathVariable UUID userId){
        return new ApiResponse<UserDto>(true, successMessage, HttpStatus.OK, userService.get(userId));
    }

    @PostMapping("/change-password")
    public ApiResponse changePassword(@RequestBody PasswordDto password){
        userService.changePassword(password);
        return new ApiResponse<>(true, "Password Changed Successfully !!", HttpStatus.OK, new ArrayList<>());
    }

    private ApiResponse<List<UserDto>> ratingHotelListFallbackMethod(Exception e){
        List<UserDto> userDtoList = new ArrayList<>();
        UserDto userDto = new UserDto(UUID.randomUUID(), "dummy@gmail.com","Dummy","Dummy", Role.USER,"dummy location", "Server is down !! try after some time", false, new ArrayList<>());
        userDtoList.add(userDto);
        return new ApiResponse<List<UserDto>>(false, e.getMessage(), HttpStatus.FAILED_DEPENDENCY, userDtoList);
    }

    private ApiResponse<UserDto> ratingHotelFallbackMethod(UUID userId, Exception e){
      UserDto userDto = new UserDto(UUID.randomUUID(), "dummy@gmail.com","Dummy", "Dummy", Role.USER,"dummy location", "Server is down !! try after some time", false, new ArrayList<>());
      return new ApiResponse<UserDto>(false, e.getMessage(), HttpStatus.FAILED_DEPENDENCY, userDto);
    }
}
