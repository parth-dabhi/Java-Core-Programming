package com.collectionFramework.sortingClasses;

import java.util.*;

public class ComparatorFunctionalInterface {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>(Arrays.asList("banana", "apple", "mango", "date"));

        Comparator<String> stringComparator = (a, b) -> a.length() - b.length();
        // Comparator result explanation:
        // Order:
        //   Negative (< 0): a comes first before b
        //   Positive (> 0): b comes first before a
        //   Zero (0): Order doesn’t matter (considered equal)

        words.sort(stringComparator); // smallest first - based on word length.

        System.out.println(words); // Output: [date, apple, mango, banana]

        // Compartor with Objects

        List<Student> studentList = new ArrayList<>(
                Arrays.asList(
                        new Student("Krishna", 3.8),
                        new Student("Lalita", 3.6),
                        new Student("Radhika", 4.0),
                        new Student("Sudevi", 3.6)
                )
        );

        Comparator<Student> studentComparator = Comparator.comparingDouble(Student::gpa) // first compare by gpa (ascending order)
                        .reversed() // highest gpa first (descending order)
                        .thenComparing(Student::name); // if gpa is same, then compare by name (alphabetical order)

        // Note: this Comparator chaining will reduce writing comparator logic manually and also avoid mistakes.

        studentList.sort(studentComparator);
//        Collections.sort( studentList, studentComparator); // both ways to sort

        System.out.println(studentList);
    }
}

record Student(String name, Double gpa) {}