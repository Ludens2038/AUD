package at.fhooe.mc.aud.aufgabe01;

import at.fhooe.mc.aud.myhashset.MyHashSet;

import java.util.LinkedList;

public class ChainingHashSet implements MyHashSet {

    private LinkedList[] table;
    private int tableSize;

    public ChainingHashSet(int size) {
        this.table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList();
        }
        this.tableSize = size;
    }

    @Override
    public int size() {
        return this.tableSize;
    }

    @Override
    public boolean insert(Object key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("given key is null");
        }

        if (!contains(key)) {
            table[key.hashCode() % this.tableSize].add(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("given key is null");
        }

        return table[key.hashCode() % this.tableSize].contains(key);
    }

    @Override
    public boolean remove(Object key) throws IllegalArgumentException {

        if (key == null) {
            throw new IllegalArgumentException("given key is null");
        }

        int hashKey = key.hashCode() % this.tableSize;

        if (table[hashKey].contains(key)) {
            return table[hashKey].remove(key);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ChainingHashSet: \n");
        for (int i = 0; i < this.table.length; i++) {
            builder.append(i + ": " + this.table[i].toString() + "\n");
        }
        return builder.toString();
    }



    @Override
    public void clear() {
        for (int i = 0; i < this.table.length; i++) {
            this.table[i].clear();
        }
    }
}
