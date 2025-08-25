package com.collectionFramework.list.linkedList;

import java.util.*;

public class LinkedListClass {
    public static void main(String[] args) {

        // Add Element to LinkedList

        LinkedList<Object> list = new LinkedList<>();
        list.add("One");
        list.add(2);
        list.add(null);
        list.add("Four4");
        System.out.println("Initial LinkedList order: " + list);

        list.add(3, "Three");

        // Adding an element at the first position of list using addFirst() method of Deque interface.
        System.out.println("LinkedList Elements after adding the first element");
        list.addFirst("Zero");
        System.out.println(list);

        // Adding an element at the end of the list using addLast() method of Deque interface.
        System.out.println("LinkedList Elements after adding the last element");
        list.addLast("Five");
        System.out.println(list);

        // Adding all elements from existing ArrayList collection to the end of the LinkedList.

        // Call addAll() method to add all elements to the end of the linked list.
        list.addAll(Arrays.asList("six", 7, "eight"));
//        list.addAll(3, Arrays.asList("six", 7, "eight")); // at specific index!
        System.out.println("LinkedList Elements after adding all elements from ArrayList");
        System.out.println(list);

        // Adding all elements from an existing Vector collection at the specified position of LinkedList.
        Vector<Double> doubleVector = new Vector<>(Arrays.asList(7.5, 7.9));
        list.addAll(9, doubleVector);

        System.out.println("Linkedlist elements after adding all elements from vector");
        System.out.println(list);

        // Iterate LinkedList In Best Way!

        /*
            There are five ways in which LinkedList can be iterated in Java. They are as follows:

            For loop - don't use
            Advanced For loop - don't use
            While loop - don't use
            Iterator - Optimal(2)
            ListIterator - Optimal(1)
        */

        LinkedList<Character> characterLinkedList = new LinkedList<>();

        characterLinkedList.add('A');
        characterLinkedList.add('B');
        characterLinkedList.add('C');
        characterLinkedList.add('D');
        characterLinkedList.add('E');

        System.out.println("**Using Iterator**");

        Iterator<Character> characterIterator = characterLinkedList.iterator();

        while (characterIterator.hasNext()) {
            System.out.println(characterIterator.next());
        }

        /*
            ListIterator is the most powerful iterator that allows us to traverse the LinkedList' both forward and backward.'
            The advantage of using ListIterator to traverse over a LinkedList in Java is that it offers additional methods
            for bidirectional iteration and modification of the list during iteration.
        */

        System.out.println("LinkedList original order");
        System.out.println(characterLinkedList);

//        ListIterator<Character> characterListIterator = characterLinkedList.listIterator();
        ListIterator<Character> characterListIterator = characterLinkedList.listIterator(2); // start from index 2

        System.out.println("Interating in forward direction");

        while (characterListIterator.hasNext()) {
            System.out.println(characterListIterator.next());
        }

        System.out.println("Iterating in backwrd direction");

        while (characterListIterator.hasPrevious()) {
            Character previous = characterListIterator.previous();
            System.out.println(previous);
            if (previous.equals('C'))
                characterListIterator.remove(); // always use iterator's remove method to avoid ConcurrentModificationException
        }

        System.out.println(characterLinkedList);
    }
}
