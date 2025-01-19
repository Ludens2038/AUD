package at.fhooe.mc.aud.aufgabe02;

import at.fhooe.mc.aud.myhashset.MyHashSet;

public class OrderedDoubleHashSet implements MyHashSet {
    public Object[] table; //change to private after testing
    private int size;
    private int entries;

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
        int h1 = Math.abs(key.hashCode()) % this.size;
        int h2 = 1 + Math.abs((key.hashCode()) % 5);

        Object current = key;
        Object swapper = null;
        int currentHash = current.hashCode();

        while (this.table[h1] != null) {
            if (this.table[h1].hashCode() > currentHash) {
                swapper = this.table[h1];
                this.table[h1] = current;
                current = swapper;
                currentHash = current.hashCode();
                h2 = 1 + Math.abs((currentHash % 5));
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

        int h1 = Math.abs(key.hashCode()) % this.size;
        int h2 = 1 + Math.abs(key.hashCode()) % 5;

        while (this.table[h1] != null) {
            if (key.equals(this.table[h1])) {
                return true;
            } else if (this.table[h1] instanceof Deleted) {
                h1 = (h1 + h2) % this.size;
            } else if (this.table[h1].hashCode() > key.hashCode()) {
                return false;
            } else {
                h1 = (h1 + h2) % this.size;
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object key) throws IllegalArgumentException {

        if (key == null) {
            throw new IllegalArgumentException("given key is null");
        }

        int h1 = Math.abs(key.hashCode()) % this.size;
        int h2 = 1 + Math.abs(key.hashCode()) % 5;

        while (this.table[h1] != null) {
            if (key.equals(this.table[h1])) {
                this.table[h1] = new Deleted();
                return true;
            } else if (this.table[h1] instanceof Deleted) {
                h1 = (h1 + h2) % this.size;
            } else if (this.table[h1].hashCode() > key.hashCode()) {
                return false;
            }
            h1 = (h1 + h2) % this.size;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("OrderedDoubleHashSet: \n");
        for (int i = 0; i < this.table.length; i++) {
            if (this.table[i] == null) {
                builder.append(i + ": null\n");
                continue;
            } else if (this.table[i] instanceof Deleted) {
                builder.append(i + ": Deleted\n");
                continue;
            }
            builder.append(i + ": " + this.table[i].toString() + "\n");
        }
        return builder.toString();
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.table.length; i++) {
            this.table[i] = null;
        }
        this.entries = 0;
    }
}
