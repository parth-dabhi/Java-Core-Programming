package com.java8Features.importantFunctionalInterfaces;

import java.util.HashMap;
import java.util.Optional;

@FunctionalInterface
interface Converter<F,T> {
    T convert(F form);
}

public class CustomFunctionalInterface {
    public static void main(String[] args) {
        // Example - 1
        Converter<String, Integer> stringIntegerConverter = Integer::valueOf;
//      Converter<String, Integer> stringIntegerConverter = (str) -> Integer.valueOf(str);
        Integer converted = stringIntegerConverter.convert("1947");
        System.out.println(converted);

        // Example - 2
        Converter<String, String> stringStringConverter = String::toUpperCase;
        String converted1 = stringStringConverter.convert("Radha");
        System.out.println(converted1);
    }
}   