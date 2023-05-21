package com.UserService.resource;

import com.UserService.domain.User;
import com.UserService.dto.ApiLoginRequest;
import com.UserService.dto.ApiLoginResponse;
import com.UserService.dto.ApiResponse;
import com.UserService.service.AuthService;
import com.UserService.service.JwtService;
import com.UserService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    private final UserService userService;
    private final AuthService authService;
    private final JwtService jwtService;

    public AuthResource(UserService userService, AuthService authService, JwtService jwtService) {
        this.userService = userService;
        this.authService = authService;
        this.jwtService = jwtService;
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

    @GetMapping("/validate")
    public ApiResponse validateToken(@RequestParam("token") String token) {
        jwtService.validateToken(token);
        return new ApiResponse(true,  "Token is valid", HttpStatus.OK, new ArrayList<>());
    }
}
