package com.java8Features.dateAndTimeAPI;

import java.time.LocalDate;

public class LocalDateClass {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate customDate = LocalDate.of(19745,10,3);
        LocalDate yesterday = today.minusDays(1);

        if(today.isAfter(yesterday)) {
            System.out.println("Today is after yesterday");
        } else {
            System.out.println("Today is not after yesterday");
        }

        LocalDate nextWeek = today.plusWeeks(1);
    }
}
