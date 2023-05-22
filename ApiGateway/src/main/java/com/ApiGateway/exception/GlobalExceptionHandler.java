package com.ApiGateway.exception;

import com.ApiGateway.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidToken(TokenNotValidException exception){
        HttpStatus invalidAccessStatus =  HttpStatus.UNAUTHORIZED;

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                false,
                exception.getMessage(),
                invalidAccessStatus,
                ZonedDateTime.now(ZoneId.of("z"))
        );

        return new ResponseEntity<>(exceptionResponse, invalidAccessStatus);
    }
}
