package com.collectionFramework.list.listInterface;

public interface CustomList<E> extends Iterable<E>{
    boolean isEmpty();
    int size();
    boolean add(E element);
    boolean add(int index, E element);
    boolean remove(E element);
    E remove(int index);
    E get(int index);
    int indexOf(E element);
    E set(int index, E element);
    boolean contains(E element);
    void clear();
    Object[] toArray();
}
