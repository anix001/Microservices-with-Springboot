package com.UserService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiExceptionResponse {
    private Boolean success;
    private String message;
    private HttpStatus httpStatus;
    private String requestType;
    private String requestUrl;
    private ZonedDateTime timestamp;
}
