package com.UserService.resource;

import com.UserService.domain.User;
import com.UserService.dto.ApiLoginRequest;
import com.UserService.dto.ApiLoginResponse;
import com.UserService.dto.ApiResponse;
import com.UserService.dto.VerifyOtpDto;
import com.UserService.service.AuthService;
import com.UserService.service.EmailService;
import com.UserService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    private final UserService userService;
    private final AuthService authService;
    private final EmailService emailService;

    public AuthResource(UserService userService, AuthService authService, EmailService emailService) {
        this.userService = userService;
        this.authService = authService;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody User user){
        userService.store(user);
        return new ApiResponse(true,  "Please check your mail !!", HttpStatus.CREATED, new ArrayList<>());
    }

    @PostMapping("/authenticate")
    public ApiLoginResponse authenticate(@RequestBody ApiLoginRequest apiLoginRequest){
      return new ApiLoginResponse(true, "User LoggedIn", HttpStatus.OK, authService.login(apiLoginRequest));
    }

    @PostMapping("/forgot-password")
    public ApiResponse forgotPassword(@RequestBody String userEmail){
        emailService.forgotPassword(userEmail);
        return  new ApiResponse<>(true, "Please check your mail",HttpStatus.OK,new ArrayList<>());
    }

    @PostMapping("/verify-otp")
    public ApiResponse verifyOtp(@RequestBody VerifyOtpDto otpDto){
        userService.verifyOtp(otpDto);
        return  new ApiResponse<>(true, "OTP is verified !!",HttpStatus.OK,new ArrayList<>());
    }

    @PostMapping("/regenerate-otp")
    public ApiResponse regenerateOtp(@RequestBody String email, @RequestParam(value="forgotPassword", required = false, defaultValue = "false") boolean forgotPassword){
        userService.regenerateOtp(email, forgotPassword);
        return  new ApiResponse<>(true, "New OTP is send on the mail !!",HttpStatus.CREATED,new ArrayList<>());
    }

    @PostMapping("/user-password")
    public ApiResponse setUserPassword(@RequestBody ApiLoginRequest password){
        userService.setUserPassword(password);
        return  new ApiResponse<>(true, "Password is set successfully !!",HttpStatus.OK,new ArrayList<>());
    }

}
