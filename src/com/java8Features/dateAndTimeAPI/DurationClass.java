package com.java8Features.dateAndTimeAPI;

import java.time.Duration;
import java.time.Instant;

public class DurationClass {

    public static void main(String[] args) {
        // Duration: Represents a duration of time between two points in time.
        // It can be used to measure time intervals in seconds, minutes, hours, etc., "NOT useful huge time intervals."

        Instant start = Instant.now();

        // Simulate some processing time
        for (int i = 0; i < 1_000_000; i++) {
            // Just a loop to simulate some work
        }

        Instant end = Instant.now();

        // Calculate the duration between the two instants
        Duration duration = Duration.between(start, end);

        // compare
        System.out.println(start.compareTo(end));

        // real life use case to check subscription duration
        Duration subscriptionDuration = Duration.ofDays(30); // 30 days subscription

        Instant subscriptionStart = Instant.now();

        Instant subscriptionEnd = subscriptionStart.plus(subscriptionDuration);

        // Check if the subscription is still valid
        if (Instant.now().isBefore(subscriptionEnd)) {
            System.out.println("Subscription is still valid.");
        } else {
            System.out.println("Subscription has expired.");
        }
    }
}
