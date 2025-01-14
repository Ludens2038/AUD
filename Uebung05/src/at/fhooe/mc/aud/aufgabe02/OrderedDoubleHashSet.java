package at.fhooe.mc.aud.aufgabe02;

import at.fhooe.mc.aud.myhashset.MyHashSet;

// ordere doublehashing (kleinere hat vorrang, dh beim vergliech wird kleinere eigesetzt, die größere wird weitergegeben)
public class OrderedDoubleHashSet implements MyHashSet {
    Object[] table;
    int[] deletionCheck;
    int size;
    int entries;

    public OrderedDoubleHashSet(int size) {
        this.table = new Object[size];
        this.deletionCheck = new int[size];
        this.size = size;
        this.entries = 0;
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

        if (this.entries >= this.size) {
            System.out.println("Set is full");
            return false;
        }

        int h1 = key.hashCode() % this.size;
        int h2 = 1 + key.hashCode() % 5;

        while (this.table != null) {
            h1 = (h1 + h2) % entries;
        }

        if (deletionCheck[h1] == 0) {
            this.table[h1] = key;
            this.deletionCheck[h1] = 1;
        }

        return true;
    }

    @Override
    public boolean contains(Object key) throws IllegalArgumentException {

        if (key == null) {
            throw new IllegalArgumentException("given key is null");
        }

        int h1 = key.hashCode() % this.size;
        int h2 = 1 + key.hashCode() % 5;
        boolean found = false;

        while (this.table != null) {
            h1 = (h1 + h2) % entries;
            if (key.equals(this.table[h1]) && deletionCheck[h1] == 1) {
                found = true;
                break;
            }
        }
        return found;
    }

    @Override
    public boolean remove(Object key) throws IllegalArgumentException {


        return false;
    }

    @Override
    public void clear() {

    }
}
