package com.java8Features.streamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamPlaying {

    public static void main(String[] args) {
        // TODO : No. of ways to create Stream

        List<String> list = Arrays.asList("apple", "banana", "cherry");
        Stream<String> myStream = list.stream();

        String[] array = {"apple", "banana", "cherry" };
        Stream<String> stream = Arrays.stream(array);

        // 1,2,3
        Stream<Integer> intgerStream = Stream.of(1, 2, 3);

        // 0,1,2,3,4,5,..................,100
        Stream<Integer> limit = Stream.iterate(0, n -> n + 1).limit(100);

        // 0,1,2,3,4 - maybe
        Stream<Integer> limit1 = Stream.generate(() -> (int) Math.random() * 100).limit(5);
    }
}
