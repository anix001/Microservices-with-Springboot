package com.ApiGateway.exception;

public class TokenNotValidException extends RuntimeException{
    public TokenNotValidException() {
        super("Token is not Valid");
    }

    public TokenNotValidException(String message) {
        super(message);
    }
}
