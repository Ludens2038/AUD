package at.fhooe.sail.mc.aud.ex03.task1;

import java.util.NoSuchElementException;

public interface MyMinHeap<T extends Comparable<T>> {
    public boolean isEmpty(); //true if Heap is empty, false otherwise

    public int size(); //returns the amount of elements in the Heap

    public void insert(T val) //inserts the val data into the Heap
            throws IllegalArgumentException;

    public T removeMin() //removes and returns the minimum element
            throws NoSuchElementException;

    public T min() //returns the minimum element
            throws NoSuchElementException;

    public Object[] toArray(); //returns an array representation of the Heap

    public String toString(); //returns a string representation of the Heap
}
