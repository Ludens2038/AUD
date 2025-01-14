package at.fhooe.mc.aud.aufgabe01;

import at.fhooe.mc.aud.myhashset.MyHashSet;

import java.util.LinkedList;

public class ChainingHashSet implements MyHashSet {

    private LinkedList[] table;
    private int size;

    public ChainingHashSet(int size) {
        this.table = new LinkedList[size];
        this.size = size;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean insert(Object key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("given key is null");
        }

        if (!contains(key)) {
            table[key.hashCode() % this.size].add(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("given key is null");
        }

        return table[key.hashCode() % this.size].contains(key);
    }

    @Override
    public boolean remove(Object key) throws IllegalArgumentException {

        if (key == null) {
            throw new IllegalArgumentException("given key is null");
        }

        int hashKey = key.hashCode() % this.size;

        if (table[hashKey].contains(key)) {
            return table[hashKey].remove(key);
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            table[i] = null;
        }
    }
}
