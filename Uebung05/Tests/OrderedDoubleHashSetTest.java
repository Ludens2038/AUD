import at.fhooe.mc.aud.aufgabe02.OrderedDoubleHashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderedDoubleHashSetTest {

    OrderedDoubleHashSet empty, filled;

    @BeforeEach
    void setUp() {
        empty = new OrderedDoubleHashSet(7);

        filled = new OrderedDoubleHashSet(7);
        filled.insert(7);
        filled.insert(1);
        filled.insert(2);
        filled.insert(33);
        filled.insert(19);
        filled.insert(12);
    }

    @Test
    void testSize() {
        assertEquals(7, empty.size());
        assertEquals(7, filled.size());
    }

    @Test
    void testInsert() {
        assertTrue(empty.insert(7));
        assertTrue(empty.insert(1));
        assertFalse(empty.insert(1));

        assertFalse(filled.insert(7));

        filled.insert(11);
        assertFalse(filled.insert(73));
    }

    @Test
    void testContains() {
        assertFalse(empty.contains(7));
        assertTrue(filled.contains(7));
    }

    @Test
    void testClear() {
        filled.clear();
        assertFalse(filled.contains(7));
        assertFalse(filled.contains(1));
        assertFalse(filled.contains(2));
        assertFalse(filled.contains(33));
        assertFalse(filled.contains(19));
        assertFalse(filled.contains(12));
    }

    @Test
    void testInsertNullException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            empty.insert(null);
        });
        assertEquals("given key is null", exception.getMessage());
    }

    @Test
    void testContainsNullException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            empty.contains(null);
        });
        assertEquals("given key is null", exception.getMessage());
    }

    @Test
    void testRemoveNullException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            empty.remove(null);
        });
        assertEquals("given key is null", exception.getMessage());
    }
}
