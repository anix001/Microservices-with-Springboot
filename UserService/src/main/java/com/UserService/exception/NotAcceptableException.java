package com.UserService.exception;

public class NotAcceptableException extends RuntimeException{
    public NotAcceptableException() {
        super("Not Acceptable Request");
    }

    public NotAcceptableException(String message) {
        super(message);
    }
}
