package com.collectionFramework.list.stack;

import java.util.LinkedList;
import java.util.Stack;

// it has all methods of vector class
// it is a legacy class
// it is synchronized

public class StackClass {
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(10);
        st.push(20);
        st.push(30);
        System.out.println("Stack: " + st);
        System.out.println("Popped element: " + st.pop());
        System.out.println("Stack after pop: " + st);
        System.out.println("Top element: " + st.peek());
        System.out.println("Is stack empty? " + st.isEmpty());
        System.out.println("Stack size: " + st.size());

        System.out.println("Search for 20: " + st.search(20));
        System.out.println("Search for 40: " + st.search(40));

        st.push(40);
        System.out.println("Stack after pushing 40: " + st);

        System.out.println("Iterating through stack:");
        for (Integer num : st) {
            System.out.println(num);
        }

        st.clear();

        // Stack using LinkedList

        LinkedList<Integer> llStack = new LinkedList<>();
        llStack.push(100);
        llStack.push(200);
        llStack.push(300);
        System.out.println("LinkedList Stack: " + llStack);
        System.out.println("Popped element from LinkedList Stack: " + llStack.pop());
        System.out.println("LinkedList Stack after pop: " + llStack);
        System.out.println("Top element of LinkedList Stack: " + llStack.peek());
        System.out.println("Is LinkedList Stack empty? " + llStack.isEmpty());
        System.out.println("LinkedList Stack size: " + llStack.size());
        llStack.clear();

        System.out.println("Final LinkedList Stack after clear: " + llStack);
    }
}
