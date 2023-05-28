package com.UserService.exception;

import com.UserService.dto.ApiExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> handleNotFoundException(NotFoundException exception, HttpServletRequest request){
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        ApiExceptionResponse response = new ApiExceptionResponse(
                false,
                exception.getMessage(),
                notFound,
                request.getMethod(),
                request.getRequestURI(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return  new ResponseEntity<>(response, notFound);
    }

    @ExceptionHandler(NotAcceptableException.class)
    public ResponseEntity<ApiExceptionResponse> handleNotAcceptableException(NotAcceptableException exception, HttpServletRequest request){
        HttpStatus notAcceptable = HttpStatus.NOT_ACCEPTABLE;

        ApiExceptionResponse response = new ApiExceptionResponse(
                false,
                exception.getMessage(),
                notAcceptable,
                request.getMethod(),
                request.getRequestURI(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return  new ResponseEntity<>(response, notAcceptable);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiExceptionResponse> handleUnauthorizedException(UnauthorizedException exception, HttpServletRequest request){
        HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;

        ApiExceptionResponse response = new ApiExceptionResponse(
                false,
                exception.getMessage(),
                unauthorized,
                request.getMethod(),
                request.getRequestURI(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(response, unauthorized);
    }
}
