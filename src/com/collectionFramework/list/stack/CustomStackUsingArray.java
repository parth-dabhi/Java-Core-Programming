package com.collectionFramework.list.stack;

public class CustomStackUsingArray {
    private int[] stack;
    private int top;
    private int capacity;

    // Constructor to initialize the stack with a given capacity
    public CustomStackUsingArray(int capacity) {
        this.capacity = capacity;
        this.stack = new int[capacity];
        this.top = -1;
    }

    // Push method to add an element to the top of the stack
    public void push(int data) {
        if (top == capacity - 1) {
            throw new IllegalStateException("Stack is full");
        }
        stack[++top] = data;
    }

    // Pop method to remove and return the top element of the stack
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top--];
    }

    // Peek method to view the top element without removing it
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top];
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Method to get the current size of the stack
    public int size() {
        return top + 1;
    }
}
