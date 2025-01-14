package at.fhooe.mc.aud.aufgabe02;

import at.fhooe.mc.aud.myhashset.MyHashSet;

// ordere doublehashing (kleinere hat vorrang, dh beim vergliech wird kleinere eigesetzt, die größere wird weitergegeben)
public class OrderedDoubleHashSet implements MyHashSet {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean insert(Object key) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean contains(Object key) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean remove(Object key) throws IllegalArgumentException {
        return false;
    }

    @Override
    public void clear() {

    }
}
