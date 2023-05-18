package com.RatingService.exception;

import com.RatingService.dto.ApiExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> handlerResourceNotFoundException(ResourceNotFoundException exception){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiExceptionResponse response = new ApiExceptionResponse(
                false,
                exception.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return  new ResponseEntity<>(response, badRequest);
    }
}
