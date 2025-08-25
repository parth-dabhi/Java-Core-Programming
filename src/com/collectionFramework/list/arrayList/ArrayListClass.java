package com.collectionFramework.list.arrayList;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayListClass {
    public static void main(String[] args) {
        // Problem - 1
        ArrayList<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        list1.ensureCapacity(5);
//        list2.ensureCapacity(5); Problem here!

        /*
        In Java polymorphism:

        Reference type decides what methods are accessible at compile time.
        Object type decides which implementation of that method will be executed at runtime.
        */

        ArrayList<Integer> list3 = new ArrayList<>();
        list3.add(1);
        list3.add(2);
        list3.add(3);
//        list3.add(null);
        list3.addFirst(11);
        list3.addLast(33);
        list3.addAll(1, Arrays.asList(101,102,103)); // important
        System.out.println(list3);

        System.out.println("Size: " + list3.size());

        for (Integer item : list3) {
            System.out.println(item);
        }

        for (int item : list3) { // Unboxing - same result
            System.out.println(item);
        }

        Iterator<Integer> iterator = list3.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println(list3.contains(3)); // true

        list3.add(1,20);

        System.out.println(list3);

        list3.sort(Integer::compare);
        System.out.println(list3);

        // List to array
        Integer[] list3Array = list3.toArray(new Integer[0]); // we need Integer type of array, that's why we passed new Integer[0]

        Integer[] ints = new Integer[0];
        int length = ints.length;
        System.out.println(length);

        // Important Logical Question
        List<Integer> numList = new ArrayList<>(Arrays.asList(1,2,3));
        numList.remove(1); // remove with index, remove at index 1 - {1,3}
        numList.add(1,2);
        numList.remove(Integer.valueOf(1)); // remove with stored Object Value, remove Integer object 2 - {2,3}
        System.out.println(numList);

        List<String> stringList = Arrays.asList("One", "Two", "Three"); // which Returns a fixed-size list - not extend enable
//        stringList.add("Four"); // throw exception
        ArrayList<String> list4 = new ArrayList<>(stringList);
        System.out.println(list4);

        List<String> studentName = List.of("Krishna", "Radhika", "Lalita"); // Returns an unmodifiable list containing three elements
        list4 = new ArrayList<>(studentName);
        System.out.println(list4);


        List<String> unmodifiableList = Collections.unmodifiableList(list4); // Returns an unmodifiable view of the specified list.
        List<String> copied = List.copyOf(list4); // Returns an unmodifiable List containing the elements of the given Collection
        List<String> collected = list4.stream().collect(Collectors.toUnmodifiableList()); // Returns an unmodifiable List
        List<String> collected2 = list4.stream().toList();// Returns an unmodifiable List

    }
}

