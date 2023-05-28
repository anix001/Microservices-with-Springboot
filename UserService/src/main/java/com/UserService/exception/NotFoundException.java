package com.UserService.exception;

public class NotFoundException extends  RuntimeException{

    public NotFoundException() {
        super("Resource is not found on the server !!");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
