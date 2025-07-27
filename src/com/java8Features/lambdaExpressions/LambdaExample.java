package com.java8Features.lambdaExpressions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LambdaExample {
    public static void main(String[] args) {
        List<String> languages = Arrays.asList("Java", "Python", "JavaScript", "C++");
        System.out.println("Languages which starts with 'J':");

        filter(languages, (str) -> str.startsWith("J"));
    }

    private static void filter(List<String> languages, Predicate<String> stringPredicate) {
        for(String language : languages){
            if (stringPredicate.test(language)) {
                System.out.println("name :" + language);
            }
        }
    }
}
