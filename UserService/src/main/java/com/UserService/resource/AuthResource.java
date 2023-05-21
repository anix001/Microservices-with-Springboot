package com.UserService.resource;

import com.UserService.domain.User;
import com.UserService.dto.ApiLoginRequest;
import com.UserService.dto.ApiLoginResponse;
import com.UserService.dto.ApiResponse;
import com.UserService.service.AuthService;
import com.UserService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    private final UserService userService;
    private final AuthService authService;

    public AuthResource(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody User user){
        userService.store(user);
        return new ApiResponse(true,  "User Registered Successfully !!", HttpStatus.CREATED, new ArrayList<>());
    }

    @PostMapping("/authenticate")
    public ApiLoginResponse authenticate(@RequestBody ApiLoginRequest apiLoginRequest){
      return new ApiLoginResponse(true, "User LoggedIn", HttpStatus.OK, authService.login(apiLoginRequest));
    }

}
