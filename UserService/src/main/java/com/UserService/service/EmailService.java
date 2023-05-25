package com.UserService.service;

public interface EmailService {
    void verifyAccount(String to);
    void forgotPassword(String to);
}
