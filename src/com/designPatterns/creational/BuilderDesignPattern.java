/*
🧱 What is Builder Design Pattern?

The Builder Design Pattern is a creational design pattern that is used to build complex objects step-by-step.
It helps to construct objects with many optional parameters without needing multiple constructors (constructor overloading).
we use the Builder pattern to create custom objects like:
Burger burger = new Burger.Builder("Veg")
                    .addCheese(true)
                    .addLettuce(true)
                    .addSauce("Spicy Mayo")
                    .build();

🤯 Problem Without Builder (Telescoping Constructor Anti-pattern)
public class User {
    public User(String name) { ... }
    public User(String name, String email) { ... }
    public User(String name, String email, String phone) { ... }
    // And so on...
}

Theory:

- Creational design pattern
- Used when we have too many arguments to send in Constructor \& it's hard to maintain the order.
- When we don't want to send all parameters in Object initialisation
- (Generally we send optional parameters as Null)

* How we will achieve this.

- We create a 'static nested class', which contains all arguments of outer class.
- As per naming convention, If class name is 'Vehicle', builder class should be 'VehicleBuilder'
- Builder class have a public constructor with all 'required parameters'.
-* Builder class should have methods for 'optional parameters'. Method will return the Builder object.
- A 'build()' method that will return the final Object.
- The main class 'Vehicle' has private constructor 'so to create instance only via Builder class.'
- The main class 'Vehicle' has only getters.
 */


package com.designPatterns.creational;

import lombok.Builder;
import lombok.ToString;

class User {
    // Required parameters
    private final String firstName;
    private final String lastName;

    // Optional parameters
    private final int age;
    private final String phone;
    private final String address;

    // Private constructor to enforce immutability
    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    // Getters only (no setters — immutability)
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    // Static nested Builder class

    static class UserBuilder {
        // Required parameters
        private final String firstName;
        private final String lastName;

        // Optional parameters
        private int age;
        private String phone;
        private String address;

        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

// Using Lombok-Based Builder (Industry Favorite)
@Builder
@ToString
class UserUsingLombok {
    private final String firstName;
    private final String lastName;
    private int age;
    private String phone;
    private String address;

    // with required field
    private UserUsingLombok(String firstName, String lastName, int age, String phone, String address) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("firstName and lastName are required");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.address = address;
    }
}

//Client Code (Fluent and Safe)
public class BuilderDesignPattern {
    public static void main(String[] args) {
        User user = new User.UserBuilder("Radha", "Krishna")
                .age(15)
                .address("Vrindavan")
                .phone("Shri Harivansh")
                .build();

        UserUsingLombok userUsingLombok = UserUsingLombok.builder()
                .firstName("Radha")
                .lastName("Rani") // age not given
                .address("Vrindavan")
                .phone("Shri Harivansh")
                .build();

        System.out.println(user);
        System.out.println(userUsingLombok);
    }
}
