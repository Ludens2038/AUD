package at.fhooe.sail.mc.aud.ex03.task1;

import java.util.NoSuchElementException;

public class MinHeap<T extends Comparable<T>> implements MyMinHeap<T>{

    T[] heap;
    int size;
    int container = 0;

    public MinHeap(int _initCapacity) {
        if (_initCapacity < 1){
            _initCapacity = 1;
        }

        T[] temp = (T[])new Comparable[_initCapacity];
        heap = temp;
        size = 0;
    }

    private void upHeap(int index) {

        int up = parent(index);

        if (up < 0) {
            return;
        }

        if (heap[index].compareTo(heap[parent(index)]) < 0) {
            swap(index, parent(index));
            upHeap(parent(index));
        }
    }

    private void downHeap(int index) {

        if (size < this.leftChild(index) || size < this.rightChild(index)) {
            return;
        }

        if (heap[leftChild(index)].compareTo(heap[rightChild(index)]) < 0 ) {
            int down = leftChild(index);
            swap(index, leftChild(index));
            downHeap(down);
        } else if (heap[leftChild(index)].compareTo(heap[rightChild(index)]) > 0) {
            int down = rightChild(index);
            swap(index, rightChild(index));
            downHeap(down);
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

        // fragen ob das so gemeint war
        if (size == this.heap.length) {
            T[] newHeap = (T[])new Comparable[size * 2];
            System.arraycopy(heap, 0, newHeap, 0, heap.length);
            heap = newHeap;
        }

        if (val == null) {
            throw new IllegalArgumentException();
        }

        size++;
        this.heap[size - 1] = val;

        if (val.compareTo(heap[parent(size - 1)]) < 0) {
            upHeap(size - 1);
        }
    }

    @Override
    public T removeMin() throws NoSuchElementException {

        if (size == 0){
            throw new NoSuchElementException();
        }

        T temp = this.heap[0];
        this.swap(0, size - 1);
        size--;
        downHeap(0);

        return temp;
    }

    @Override
    public T min() throws NoSuchElementException {

        if (size == 0) {
            throw new NoSuchElementException();
        }

        return this.heap[0];
    }

    @Override
    public Object[] toArray() {
        Object[] o = new Object[this.size];

        for (int i = 0; i < o.length; i++) {
            o[i] = this.heap[i];
        }
        return o;
    }

    /**
     * Se beginining of se tasque 2
     */

    // muss in place sein, bototm up heap aus den folien umsetzen
    public MinHeap(T list[]) throws IllegalArgumentException {

        if (list == null) {
            throw new IllegalArgumentException();
        }

        this.heap = list;
        this.size = list.length;

        int leaves = (list.length + 1) / 2;

        for(int i = leaves - 1; i >= 0; i--) {
            this.downHeap(i);
        }

    }
    public boolean contains(T val) throws IllegalArgumentException {

        if (val == null) {
            throw new IllegalArgumentException();
        }
        return seeker(val, 0);
    }

    private boolean seeker(T val, int index) {

        if (index >= this.size) {
            return false;
        } else if (val.compareTo(this.heap[index]) == 0) {
            return true;
        } else {
            return (seeker(val,leftChild(index)) || seeker(val, rightChild(index)));
        }
    }

    public static<T extends Comparable<T>> void sort(T list[]) throws IllegalArgumentException {

        if (list == null) {
            throw new IllegalArgumentException();
        }



    }
}
