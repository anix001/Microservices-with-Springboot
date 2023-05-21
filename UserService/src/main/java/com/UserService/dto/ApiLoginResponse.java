package com.UserService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiLoginResponse {
    private Boolean success;
    private String message;
    private HttpStatus httpStatus;
    private String accessToken;
}
