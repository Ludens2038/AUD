import at.fhooe.mc.aud.aufgabe01.ChainingHashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChainingHashSetTest {

    ChainingHashSet filled, empty;

    @BeforeEach
    public void setUp() {
        empty = new ChainingHashSet(7);

        filled = new ChainingHashSet(7);
        filled.insert(1);
        filled.insert(2);
        filled.insert(3);
        filled.insert(4);
        filled.insert(5);
        filled.insert(6);
        filled.insert(7);
    }

    @Test
    public void testSize() {
        assertEquals(7, filled.size());
        assertEquals(7, empty.size());
    }

    @Test
    public void testInsert() {
        assertTrue(filled.insert(8));
        assertTrue(filled.insert(9));
        assertTrue(filled.insert(14));
        assertFalse(filled.insert(1));
        assertFalse(filled.insert(2));

        assertTrue(empty.insert(1));
        assertTrue(empty.insert(2));
        assertTrue(empty.insert(3));
        assertTrue(empty.insert(4));
        assertFalse(empty.insert(1));
        assertFalse(empty.insert(2));
        assertFalse(empty.insert(3));
        assertFalse(empty.insert(4));
    }

    @Test
    public void testContains() {
        assertTrue(filled.contains(1));
        assertTrue(filled.contains(2));
        assertTrue(filled.contains(3));
        assertTrue(filled.contains(4));
        assertTrue(filled.contains(5));
        assertTrue(filled.contains(6));
        assertTrue(filled.contains(7));
        assertFalse(filled.contains(8));
        assertFalse(filled.contains(9));

        assertFalse(empty.contains(1));
        assertFalse(empty.contains(2));
        assertFalse(empty.contains(3));
        assertFalse(empty.contains(4));
    }

    @Test
    public void testRemove() {
        assertFalse(filled.remove(8));
        assertFalse(filled.remove(9));

        assertTrue(filled.remove(1));
        assertTrue(filled.remove(2));
        assertTrue(filled.remove(3));

        filled.insert(14);
        assertTrue(filled.remove(14));

        assertFalse(empty.remove(1));
        assertFalse(empty.remove(2));
        assertFalse(empty.remove(3));
        assertFalse(empty.remove(4));
    }

    @Test
    public void testClear() {
        filled.clear();
        assertFalse(filled.contains(1));
        assertFalse(filled.contains(2));
        assertFalse(filled.contains(3));
        assertFalse(filled.contains(4));
        assertFalse(filled.contains(5));
        assertFalse(filled.contains(6));
        assertFalse(filled.contains(7));
    }

    @Test
    public void testToString() {
        filled.insert(8);
        filled.insert(9);
        filled.insert(10);
        filled.insert(11);
        filled.insert(12);

        String expected = "ChainingHashSet: \n" +
                "0: [7]\n" +
                "1: [1, 8]\n" +
                "2: [2, 9]\n" +
                "3: [3, 10]\n" +
                "4: [4, 11]\n" +
                "5: [5, 12]\n" +
                "6: [6]\n";

        assertEquals(expected, filled.toString());
    }

    @Test
    public void testInsertNullException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            empty.insert(null);
        });
        assertEquals("given key is null", exception.getMessage());
    }

    @Test
    public void testContainsNullException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            empty.contains(null);
        });
        assertEquals("given key is null", exception.getMessage());
    }

    @Test
    public void testRemoveNullException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            empty.remove(null);
        });
        assertEquals("given key is null", exception.getMessage());
    }
}