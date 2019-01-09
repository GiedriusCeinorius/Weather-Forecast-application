package com.gce.weatherforecastapp.helpers;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeManager {

    public String formatCurrentDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
