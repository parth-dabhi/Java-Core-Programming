package com.java8Features.dateAndTimeAPI;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class DateTimeFormatterClass {

    public static void main(String[] args) {
        String Date = "05/10/2023";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Parse the date string into a LocalDate object
        LocalDate parseLocalDate = LocalDate.parse(Date, formatter);

        System.out.println("Parsed LocalDate: " + parseLocalDate);

        String dateTimeString = "05/10/2023 10:15:30 IST";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z");

        // Parse the date and time string into a LocalDateTime object
        LocalDateTime parseLocalDateTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        System.out.println("Parsed LocalDateTime: " + parseLocalDateTime);

        // All letters in the pattern are case-sensitive, here below is the list of letters used Across Date and Time API

        // d - Day of the month (1-31)
        // M - Month of the year (1-12)
        // y - Year (e.g., 2023)
        // H - Hour of the day (0-23)
        // m - Minute of the hour (0-59)
        // s - Second of the minute (0-59)
        // S - Fractional seconds (e.g., milliseconds, microseconds)
        // z - Time zone (e.g., IST, GMT)
        // a - AM/PM marker (e.g., AM, PM)
        // E - Day of the week (e.g., Monday, Tuesday)
        // D-Day of the year (1-365)
        // F - Day of the week in the month (1-5)
        // L - Stand-alone month (e.g., Jan, Feb)
        // w - Week of the year (1-53)
        // W - Week of the month (1-5)
        // Q - Quarter of the year (1-4)
        // k - Hour of the day (1-24)
        // K - Hour of the day (0-11)

    }
}
