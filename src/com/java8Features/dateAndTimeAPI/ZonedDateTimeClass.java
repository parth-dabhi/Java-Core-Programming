package com.java8Features.dateAndTimeAPI;

import java.time.ZonedDateTime;
import java.time.ZoneId;

public class ZonedDateTimeClass {

    public static void main(String[] args) {

        // Create a ZonedDateTime instance representing the current date and time in the system's default time zone
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("Current Date and Time with Time Zone: " + now);

        // available time zones
        System.out.println("Available Time Zones: " + ZoneId.getAvailableZoneIds().stream().limit(5).toList());

        // Create a ZonedDateTime instance for a specific date, time, and time zone
        ZonedDateTime specificZonedDateTime = ZonedDateTime.of(2023, 10, 1, 12, 30, 0, 0, ZoneId.of("America/New_York"));
        System.out.println("Specific Date and Time with Time Zone: " + specificZonedDateTime);

    }
}
