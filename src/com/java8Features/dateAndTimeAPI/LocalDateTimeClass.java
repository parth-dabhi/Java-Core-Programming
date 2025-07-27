package com.java8Features.dateAndTimeAPI;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimeClass {

    public static void main(String[] args) {
        // Create a LocalDateTime instance representing the current date and time
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current Date and Time: " + now);

        // Create a LocalDateTime instance for a specific date and time
        LocalDateTime specificDateTime = LocalDateTime.of(2023, 10, 1, 12, 30);
        System.out.println("Specific Date and Time: " + specificDateTime);

        // Add 5 days to the current date and time
        LocalDateTime futureDateTime = now.plusDays(5);
        System.out.println("Future Date and Time (after 5 days): " + futureDateTime);

        // Subtract 2 hours from the current date and time
        LocalDateTime pastDateTime = now.minusHours(2);
        System.out.println("Past Date and Time (2 hours ago): " + pastDateTime);
    }
}
