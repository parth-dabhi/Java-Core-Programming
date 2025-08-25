package com.collectionFramework.list.arrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*
* "Copy on Write" means that whenever a write operation
* Like adding or removing an element
* instead of directly modifying the existing list
* a new copy of the List is created, and the modification is applied to that copy
* This ensures that other threads reading the list white it's being modified are unaffected.
* Read Operations: Fast and direct, since they happen on a stable list without interference from modifications.
* Write Operations: A new copy of the List is created for every modification.
* The reference to the list is then updated so that subsequent reads use this new list.
*
* */

public class CopyOnWriteArrayListClass {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>(Arrays.asList( "AA", "BB", "CC", "DD", "EE"));
        System.out.println("copyOnWriteArrayList: " + copyOnWriteArrayList);

        List<String> list = new ArrayList<>(Arrays.asList( "AA", "BB", "CC", "DD", "EE"));

        for(int i = 0; i < list.size(); i++) { // it depends on size() method, not on iterator, that's why not throwing exception
            System.out.println(i + ":" + list.get(i));
            if (list.get(i).equals("CC")) {
                list.add("FF");
                System.out.println("FF added");
            }
        }
        System.out.println(list + "\n");

        List<String> list1 = new ArrayList<>(Arrays.asList( "AA", "BB", "CC", "DD", "EE"));

        try {
            for (String s : list1) {
                if (s.equals("CC")) {
                    list1.add("FF"); // will throw ConcurrentModificationException
                    System.out.println("FF added");
                }
                System.out.println(s);
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException caught: DD, EE, FF not printed");
        }
        System.out.println(list1 + "\n");

        for (String s : copyOnWriteArrayList) {
            if (s.equals("CC")) {
                copyOnWriteArrayList.add("FF"); // will NOT throw ConcurrentModificationException
                System.out.println("FF added");
            }
            System.out.println(s);
        }
        System.out.println(copyOnWriteArrayList);

        // Another Example using threads (reader and writer threads)
        System.out.println("\nExample using threads:\n");

        List<String> sharedList = new ArrayList<>(); // will throw ConcurrentModificationException
//        List<String> sharedList = new CopyOnWriteArrayList<>();
        sharedList.add("Item1");
        sharedList.add("Item2");
        sharedList.add("Item3");
        Thread readerThread = new Thread(() -> {
            try {
                while (true) {
                    // Iterate through the list
                    for (String item : sharedList) {
                        System.out.println("Reading item: " + item);
                        Thread.sleep(100); // Small delay to simulate work
                    }
                }
            } catch (Exception e) {
                System.out.println("Exception in reader thread: " + e);
            }
        });
        Thread writerThread = new Thread(() -> {
            try {
                Thread.sleep(500); // Delay to allow reading to start first
                sharedList.add("Item4");
                System.out.println("Added Item4 to the list.");

                Thread.sleep(500);
                sharedList.remove("Item1");
                System.out.println("Removed Item1 from the list.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        readerThread.start();
        writerThread.start();
    }
}
