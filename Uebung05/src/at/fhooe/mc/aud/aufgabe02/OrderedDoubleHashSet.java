package at.fhooe.mc.aud.aufgabe02;

import at.fhooe.mc.aud.myhashset.MyHashSet;

public class OrderedDoubleHashSet implements MyHashSet {
    Object[] table;
    int size;
    int entries;

    public OrderedDoubleHashSet(int size) {
        this.table = new Object[size];
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

        if (contains(key)) {
            return false;
        }

        //start of insertion
        int h1 = key.hashCode() % this.size;
        int h2 = 1 + key.hashCode() % 5;

        Object current = key;
        Object swapper = null;
        int currentHash = current.hashCode();

        while (this.table[h1] != null) {
            if (this.table[h1].hashCode() > currentHash) {
                swapper = this.table[h1];
                this.table[h1] = current;
                current = swapper;
                currentHash = current.hashCode();
            }
            h1 = (h1 + h2) % this.size;
        }

        this.table[h1] = current;
        this.entries++;

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

        while (this.table[h1] != null) {
            if (key.equals(this.table[h1])) {
                found = true;
                break;
            }
            h1 = (h1 + h2) % this.size;
        }
        return found;
    }

    @Override
    public boolean remove(Object key) throws IllegalArgumentException {
        int h1 = key.hashCode() % this.size;
        int h2 = 1 + key.hashCode() % 5;

        while (this.table[h1] != null) {
            if (key.equals(this.table[h1])) {
                this.table[h1] = null;
                return true;
            }
            h1 = (h1 + h2) % entries;
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.table.length; i++) {
            this.table[i] = null;
        }
    }
}
