package com.spotify.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;

public class DateTimeUtils {

    public static String getCurrentDateTimeInIST() {
        ZonedDateTime istTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        String formattedTime = istTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        return formattedTime;
    }
}
