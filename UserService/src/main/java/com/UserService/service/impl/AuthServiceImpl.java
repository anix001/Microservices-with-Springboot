package com.UserService.service.impl;

import com.UserService.dto.ApiLoginRequest;
import com.UserService.exception.NotFoundException;
import com.UserService.exception.UnauthorizedException;
import com.UserService.service.AuthService;
import com.UserService.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public String login(ApiLoginRequest loginRequest) {
        try{
         Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
         if(authentication.isAuthenticated()){
            return jwtService.generateToken(loginRequest.getEmail());
         }
        }catch(Exception e) {
            throw new UnauthorizedException("Invalid access !!");
        }
        return "Unauthorized";
    }
}
