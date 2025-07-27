package com.java8Features.dateAndTimeAPI;

import java.time.LocalTime;

public class LocalTimeClass {

    public static void main(String[] args) {

        // LocalTime represents a time without a date or time zone.
        // It is useful for representing times of day, such as opening hours or schedules.

        // Example: Creating a LocalTime instance
        LocalTime time = LocalTime.of(14, 30); // 2:30 PM
        System.out.println("Local Time: " + time);

        // Example: Getting the current local time
        LocalTime currentTime = LocalTime.now();
        System.out.println("Current Local Time: " + currentTime);

        // Example: Adding hours and minutes to a LocalTime
        LocalTime newTime = time.plusHours(1).plusMinutes(15);
        System.out.println("New Local Time after adding 1 hour and 15 minutes: " + newTime);

        // Parsing a LocalTime from a string
        LocalTime parsedTime = LocalTime.parse("10:15:30"); // 10:15:30 AM
        System.out.println("Parsed Local Time: " + parsedTime);

        // Example: Comparing two LocalTime instances
        LocalTime time1 = LocalTime.of(9, 0); // 9:00 AM
        LocalTime time2 = LocalTime.of(17, 0); // 5:00 PM
        if (time1.isBefore(time2)) {
            System.out.println("Time1 is before Time2");
        } else if (time1.isAfter(time2)) {
            System.out.println("Time1 is after Time2");
        } else {
            System.out.println("Time1 is equal to Time2");
        }
    }
}
