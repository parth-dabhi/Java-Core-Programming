package com.java8Features.forEachMethod;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class forEachMethodExample {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "Java");
        map.put(2, "Python");
        map.put(3, "JavaScript");
        map.put(4, "C++");

        // Using forEach method to iterate over the map
        BiConsumer<Integer, String> consumer =
                    (key, val) -> System.out.println(key + ":" + val);

        map.forEach(consumer);
    }
}
