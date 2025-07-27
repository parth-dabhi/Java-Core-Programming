package com.java8Features.optionalClass;

import java.util.Optional;

public class OptionalClassPlaying {

    // Optional Class: The `Optional` class in Java 8 is a container object used to represent the presence or absence of a value.
    // It is primarily used to avoid `null` checks and `NullPointerException`.

    // By using `Optional`, developers can specify methods to handle both the presence and absence of a value explicitly,
    // promoting cleaner and more readable code.

    public static void main(String[] args) {
        Optional<String> name = getName(2);
        Optional<String> optional = getNameById(2).map(String::toUpperCase);
        System.out.println("Optional Name: " + optional.get());

        if(name.isPresent()) {
            System.out.println("Name is: " + name.get());
        }

        // best method to handle
        name.ifPresentOrElse(n -> System.out.println("Name is: " + n), () -> System.out.println("Name not found"));
        // name.ifPresent(System.out::println); // This will print the name if present, otherwise do nothing
    }

    private static  Optional<String> getName(int id) {
        String name = null; // Simulating a scenario where name might not be found
        // Using Optional to handle the potential absence of a value
        return Optional.ofNullable(name); // with handling of null
        // return Optional.empty(); // This can also be used to represent an absent value
    }

    private static  Optional<String> getNameById(int id) {
        String name = "RadhaKrishna";
        return Optional.of(name); // without handling of null
    }
}
