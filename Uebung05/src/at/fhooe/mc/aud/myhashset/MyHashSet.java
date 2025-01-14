package at.fhooe.mc.aud.myhashset;

public interface MyHashSet {
    public int size(); // Returns the number of stored keys (these must be unique).

    public boolean insert(Object key) // Inserts the key into the set and returns true if it was
            throws IllegalArgumentException; // inserted, false otherwise.

    public boolean contains(Object key) // Returns true if the key is included in the set,
            throws IllegalArgumentException; // false otherwise.

    public boolean remove(Object key) // Removes the key from the set and returns true if it
            throws IllegalArgumentException; // was removed, false otherwise.

    public String toString(); // Returns a string representation with indication of the

    // array indices (the current structure should be recognizable).
    public void clear(); // Removes all elements from the set.
}
