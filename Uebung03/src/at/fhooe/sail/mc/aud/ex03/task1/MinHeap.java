package at.fhooe.sail.mc.aud.ex03.task1;

import java.util.NoSuchElementException;

public class MinHeap<T extends Comparable<T>> implements MyMinHeap<T>{

    T[] heap;
    int size;

    public MinHeap(int _initCapacity) {
        if (_initCapacity < 1){
            _initCapacity = 1;
        }

        T[] temp = (T[])new Comparable[_initCapacity];
        heap = temp;
        size = 0;
    }

    private void upHeap(int index) {
        if (heap[index].compareTo(heap[parent(index)]) < 0) {
            swap(index, parent(index));
        }
    }

    private void downHeap(int index) {
        if (heap[leftChild(index)].compareTo(heap[rightChild(index)]) < 0 ) {
            int down = leftChild(index);
            swap(index, leftChild(index));
//            downHeap(down);
        } else if (heap[leftChild(index)].compareTo(heap[rightChild(index)]) > 0) {
            int down = rightChild(index);
            swap(index, rightChild(index));
//            downHeap(down);
        } else {
            return;
        }
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index +1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private void swap(int index1, int index2) {
        T temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void insert(T val) throws IllegalArgumentException {
        size++;
        this.heap[size - 1] = val;
    }

    @Override
    public T removeMin() throws NoSuchElementException {
        T temp = this.heap[0];
        this.swap(0, size - 1);
        size--;
        downHeap(0);

        return temp;
    }

    @Override
    public T min() throws NoSuchElementException {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] o = new Object[this.size];

        for (int i = 0; i < o.length; i++) {
            o[i] = this.heap[i];
        }
        return o;
    }
}
