package com.java8Features.dateAndTimeAPI;

import java.time.LocalDate;
import java.time.Period;

public class PeriodClass {

    public static void main(String[] args) {
        // Represents a period of time between two dates with years, months, and days.

        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2021, 5, 1);

        // Calculate the period between two dates
        Period period = Period.between(startDate, endDate);

        System.out.println("Period between " + startDate + " and " + endDate + "  =>>  " + period);

        // custom period

        Period customPeriod = Period.of(1, 2, 3); // 1 year, 2 months, and 3 days

        System.out.println("Custom Period: " + customPeriod);


    }
}
