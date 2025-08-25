package com.collectionFramework.list.arrayList;

import com.collectionFramework.list.listInterface.CustomList;
import java.util.Arrays;
import java.util.Iterator;

public class CustomArrayList<E> implements CustomList<E> {

    // Fields
    private Object[] elementData;
    private int size = 0;
    private static final int INITIAL_CAPACITY = 10;

    // Constructors
    public CustomArrayList() {
        this.elementData = new Object[INITIAL_CAPACITY];
    }

    public CustomArrayList(int initialCapacity) {
        if(initialCapacity < 0) {
            throw new IllegalArgumentException("illegal Capacity:" + initialCapacity);
        }
        this.elementData = new Object[initialCapacity];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    private void ensureCapacity(int needCapacity) {
        if (needCapacity > elementData.length) {
            Object[] oldElements = this.elementData;
            // compute new capacity
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity / 2);

            if (newCapacity < needCapacity) { // if still too small, then
                newCapacity = needCapacity;
            }

            this.elementData = Arrays.copyOf(oldElements, newCapacity);
        }
    }

    @Override
    public boolean add(E element) {
        ensureCapacity(this.size + 1);
        elementData[size++] = element; // added element at the end.
        return true;
    }

    private void checkRangeForAdd(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Illegal index: " + index);
        }
    }

    //    For get/remove/set → valid range [0, size-1]
    private void checkRange(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("illegal index: " + index);
        }
    }

    @Override
    public boolean add(int index, E element) {
        checkRangeForAdd(index);
        ensureCapacity(this.size + 1);
        System.arraycopy(this.elementData, index, elementData, index + 1, size - index); // shift elements to the right by 1 position.

        elementData[index] = element;
        this.size++;
        return true;
    }

    /*
     * Note: Here null is handled separately because, calling .equals() on null causes a NullPointerException.
     *
     * String s = null;
     * if (s.equals("Radha")) { // ❌ NullPointerException
     * ...
     * }
     * */

    @Override
    public boolean remove(E element) {
        for (int index = 0; index < size; index++) {
            if (element == null ? elementData[index] == null : element.equals(elementData[index])) {
                remove(index);
                return true;
            }
        }
        return false;
    }

    @Override
    public E remove(int index) {
        checkRange(index);
        Object element = elementData[index];
        int movedNumber = size - index - 1;
        if (movedNumber > 0) {
            System.arraycopy(this.elementData, index+1, this.elementData, index, movedNumber);
        }
        elementData[--this.size] = null;
        return (E) element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkRange(index);
        return (E) this.elementData[index];
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < this.size; i++) {
            if (element == null ? elementData[i] == null : element.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E set(int index, E element) {
        checkRange(index);
        E oldValue = (E) this.elementData[index];
        this.elementData[index] = element;
        return oldValue;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < this.size; i++) {
            if (element == null ? elementData[i] == null : element.equals(elementData[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        Arrays.fill(this.elementData, 0, size, null);
        this.size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object[] toArray() {
        return Arrays.copyOf(this.elementData, this.size);
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator();
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(this.elementData, this.size));
    }

    // Custom Iterator
    private class CustomIterator implements Iterator<E> {

        private int current = 0;

        @Override
        public boolean hasNext(){
            return this.current < size();
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next(){
            E value = (E) elementData[current++];
            return value;
        }
    }

    // TESTING
    public static void main(String[] args) {
        CustomArrayList<String> list = new CustomArrayList<>();

        // 1. Test adding elements
        System.out.println("=== Test: Add Elements ===");
        list.add("A");
        list.add("B");
        list.add("C");
        list.add(null);  // adding null
        list.add("D");
        System.out.println(list); // Expect: [A, B, C, null, D]

        // 2. Test add at index
        System.out.println("\n=== Test: Add at Index ===");
        list.add(2, "X"); // insert at index 2
        System.out.println(list); // Expect: [A, B, X, C, null, D]

        // 3. Test remove by index
        System.out.println("\n=== Test: Remove by Index ===");
        list.remove(0); // remove first element
        System.out.println(list); // Expect: [B, X, C, null, D]
        list.remove(list.size() - 1); // remove last element
        System.out.println(list); // Expect: [B, X, C, null]

        // 4. Test remove by value
        System.out.println("\n=== Test: Remove by Value ===");
        list.remove("X"); // remove middle element
        System.out.println(list); // Expect: [B, C, null]
        list.remove(null); // remove null
        System.out.println(list); // Expect: [B, C]
        list.remove("Z"); // remove non-existing element (no change)
        System.out.println(list); // Expect: [B, C]

        // 5. Test auto grow capacity
        System.out.println("\n=== Test: Auto Resize ===");
        for (int i = 0; i < 20; i++) {
            list.add("E" + i);
        }
        System.out.println(list); // Should show many elements without error

        // 6. Test exceptions
        System.out.println("\n=== Test: Exceptions ===");
        try {
            list.add(-1, "Invalid");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        try {
            list.remove(100); // invalid index
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        System.out.println("\n=== Final List State ===");
        System.out.println(list);
    }
}
