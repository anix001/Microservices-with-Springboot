package com.UserService.exception;

public class ResourceNotFoundException extends  RuntimeException{

    public ResourceNotFoundException() {
        super("Resource is not found on the server !!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
