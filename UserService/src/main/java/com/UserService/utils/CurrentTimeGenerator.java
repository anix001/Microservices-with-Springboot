package com.UserService.utils;

import com.UserService.exception.NotAcceptableException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class CurrentTimeGenerator {

    public String getCurrentTime(){
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return time.format(formatter);
    }

    public String getDiffBetweenTwoTime(String time1, String time2) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);
            long difference = date2.getTime() - date1.getTime();
            long diffMinutes = difference / (60 * 1000) % 60;
            return Long.toString(diffMinutes);
        }catch (Exception e){
            throw new NotAcceptableException("Time format does not match");
        }
    }
}
