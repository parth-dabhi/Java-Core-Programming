package com.collectionFramework.list.linkedList;

import com.collectionFramework.list.listInterface.CustomList;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

class TestCustomLinkedList {
    public static void main(String[] args) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("List after adding elements: " + list);

        list.add(1, 15);
        System.out.println("List after adding 15 at index 1: " + list);

        list.remove((Integer) 20);
        System.out.println("List after removing element 20: " + list);

        list.remove(0);
        System.out.println("List after removing element at index 0: " + list);

        System.out.println("Element at index 1: " + list.get(1));

        System.out.println("Index of element 30: " + list.indexOf(30));

        list.set(1, 25);
        System.out.println("List after setting index 1 to 25: " + list);

        System.out.println("Does the list contain 25? " + list.contains(25));

        System.out.println("Size of the list: " + list.size());

        Object[] array = list.toArray();
        System.out.print("Array representation of the list: ");
        for (Object obj : array) {
            System.out.print(obj + " ");
        }
        System.out.println();

        list.clear();
        System.out.println("List after clearing: " + list);

        System.out.println("Is the list empty? " + list.isEmpty());

        list.add(100);
        list.add(200);
        list.add(300);
        System.out.println("List after adding elements 100 and 200: " + list);

        System.out.println("Iterating through the list using iterator() method:");

        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("Final List: " + list);
    }
}

public class CustomLinkedList<E> implements CustomList<E> {

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size = 0;
    private Node<E> first; // head
    private Node<E> last; // tail

    public CustomLinkedList() {
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean add(E element) {
        Node<E> newNode;
        if (first == null) { // Case 1: empty list
            newNode = new Node<>(null, element, null);
            first = newNode;
        } else { // Case 2: non-empty list
            newNode = new Node<>(last, element, null);
            last.next = newNode;
        }
        last = newNode;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        checkRangeForAdd(index);
        if (index == size) { // adding at the end
            return add(element);
        }

        Node<E> newNode;
        Node<E> succ = node(index);
        Node<E> pred = succ.prev;
        newNode = new Node<>(pred, element, succ); // linking new node with predecessor and successor
        succ.prev = newNode;
        if (pred == null) { // adding at the beginning
            first = newNode;
        } else {
            pred.next = newNode;
        }
        size++;
        return true;
    }

    private void checkRangeForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void checkRange(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    @Override
    public boolean remove(E element) { // remove first occurrences
        for (Node<E> x = first; x != null; x = x.next) {
            if ((element == null) ? x.item == null : x.item.equals(element)) { // handling null value element to avoid NullPointerException
                unlink(x);
                return true;
            }
        }
        return false;
    }

    @Override
    public E remove(int index) {
        checkRange(index);
        return unlink(node(index));
    }
    
    private E unlink(Node<E> x) {
        E element = x.item;
        Node<E> prev = x.prev;
        Node<E> next = x.next;

        // handling prev side
        if(prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        // handling next side
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    private Node<E> node(int index) { // returns the node at a specific index - This method helps a lot in other methods
        Node<E> x;
        if (index < (size/2)) {
            x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = last;
            for (int i = size-1; i > index; i--) {
                x = x.prev;
            }
        }
        return x;
    }

    @Override
    public E get(int index) {
        checkRange(index);
        return node(index).item;
    }

    @Override
    public int indexOf(E element) {
        int index = 0;
        for (Node<E> x = first; x != null; x = x.next) {
            if ((element == null) ? x.item == null : x.item.equals(element)) { // handling null value element to avoid NullPointerException
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public E set(int index, E element) {
        checkRange(index);
        Node<E> x = node(index);
        E oldValue = x.item;
        x.item = element;
        return oldValue;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    @Override
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next) {
            result[i++] = x.item;
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (current == null)
                    throw new NoSuchElementException();
                E data = current.item;
                current = current.next; // Important to move to next node!
                return data;
            }
        };
    }

    // toString method for linked list
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> current = first;
        while (current != null) {
            sb.append(current.item);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}