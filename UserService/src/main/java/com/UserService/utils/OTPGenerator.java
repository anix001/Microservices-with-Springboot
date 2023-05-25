package com.UserService.utils;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Random;

@Component
public class OTPGenerator {

    public String generateOTP(){
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }
}
