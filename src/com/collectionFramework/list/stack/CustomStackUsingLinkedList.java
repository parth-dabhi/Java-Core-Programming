package com.collectionFramework.list.stack;

// Custom implementation of a stack data structure using LinkedList
public class CustomStackUsingLinkedList {
    private Node top;
    private int size;

    // Node class to represent each element in the stack
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Constructor to initialize an empty stack
    public CustomStackUsingLinkedList() {
        this.top = null;
        this.size = 0;
    }

    // Push method to add an element to the top of the stack
    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // Pop method to remove and return the top element of the stack
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        int poppedData = top.data;
        top = top.next;
        size--;
        return poppedData;
    }

    // Peek method to view the top element without removing it
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.data;
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Method to get the current size of the stack
    public int size() {
        return size;
    }

    // Main method for testing the CustomStack implementation
    public static void main(String[] args) {
        CustomStackUsingLinkedList stack = new CustomStackUsingLinkedList();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top element is: " + stack.peek()); // Output: 30
        System.out.println("Stack size is: " + stack.size());  // Output: 3

        System.out.println("Popped element is: " + stack.pop()); // Output: 30
        System.out.println("Top element is: " + stack.peek());   // Output: 20
        System.out.println("Stack size is: " + stack.size());   // Output: 2
    }
}
