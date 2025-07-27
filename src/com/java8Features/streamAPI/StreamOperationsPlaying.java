package com.java8Features.streamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamOperationsPlaying {

    public static void main(String[] args) {

        System.out.println("************************* Stream 1 - EXAMPLE *************************");

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,2,20,8,16,14);

        Integer sumOfFilteredElement = list.stream()
                .filter(x -> x % 2 == 0) // filter odd number from stream and pick only even numbers - [2, 4,..., 20, 2, 20, 8, 16, 14]
                .map(x -> x / 2) // divide by 2 all - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 10, 4, 8, 7]
                .distinct() // gives only distinct elements - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
                .sorted((a,b) -> b-a) // it will sort stream in DESCENDING order. - [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
                .limit(5) // it will give only first 5 elements - [10, 9, 8, 7, 6]
                .skip(2) // now it skip first 2 elements - [8, 7, 6]
                .peek(System.out::println) // prints: 8 7 6
                .reduce(0, Integer::sum);
//                .toList(); // collect stream as list. - final result: [8, 7, 6]

        System.out.println("sum :" + sumOfFilteredElement);
//        System.out.println("Count no elements in Stream : " + fillteredList.stream().count()); // 3

        System.out.println("************************* Stream 2 - EXAMPLE *************************");

        Integer num = Stream.iterate(1, x -> x+1).limit(101) // [1, 2, ..., 101]
                .map(x -> x/20) // [0, 0, ..., 1, 1, ..., 5]
                .distinct() // [0, 1, 2, 3, 4, 5]
                .skip(1) // skip 0 → [1, 2, 3, 4, 5]
                .peek(x -> System.out.print(x + " ")) // prints: 1 2 3 4 5
                .max((a,b) -> (a-b)) // max value = 5
                .orElse(null);

        System.out.println();
        System.out.println(num); // 5
    }
}
