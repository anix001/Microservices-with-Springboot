package com.UserService.service;

import com.UserService.dto.ApiLoginRequest;
import com.UserService.dto.ApiLoginResponse;

public interface AuthService {

    String login(ApiLoginRequest loginRequest);
}
