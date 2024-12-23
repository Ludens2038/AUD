package at.fhooe.sail.mc.aud.ex03.test;

import at.fhooe.sail.mc.aud.ex03.task1.MinHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MinHeapTest {

    private MinHeap<Integer> h, h2;

    @BeforeEach
    public void setUp() {
        h = new MinHeap<Integer>(5);
        h.insert(2);
        h.insert(3);
        h.insert(4);
        h.insert(5);

        h2 = new MinHeap<Integer>(10);
    }

    @Test
    public void testInsert() {
        h.insert(1);
        assertEquals(1, h.min());
        h.insert(6);
        assertTrue(h.contains(6));
        h.insert(0);
        assertEquals(0, h.min());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(h.isEmpty());
        assertTrue(h2.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(4, h.size());
        assertEquals(0, h2.size());
    }

    @Test
    public void testToArray() {
        Object[] arr = h.toArray();
        assertEquals(4, arr.length);
    }

    @Test
    public void testBottomUp() {
        Integer[] arr = {14, 5, 3, 6, 1};
        MinHeap<Integer> h = new MinHeap<Integer>(arr);
        assertEquals(1, h.min());
    }

    @Test
    public void testSort() {
        Integer[] array = {5, 3, 8, 1, 2};
        MinHeap.sort(array);
        assertEquals(8, array[0]);
        assertEquals(5, array[1]);
        assertEquals(3, array[2]);
        assertEquals(2, array[3]);
        assertEquals(1, array[4]);
    }

    @Test
    public void testInsertNull() {
        assertThrows(IllegalArgumentException.class, () -> h.insert(null));
    }

    @Test
    public void testRemoveMinFromEmptyHeap() {
        assertThrows(NoSuchElementException.class, () -> h2.removeMin());
    }

    @Test
    public void testMinFromEmptyHeap() {
        assertThrows(NoSuchElementException.class, () -> h2.min());
    }

    @Test
    public void testContainsNull() {
        assertThrows(IllegalArgumentException.class, () -> h.contains(null));
    }

    @Test
    public void testMinHeapNull() {
        assertThrows(IllegalArgumentException.class, () -> new MinHeap<Integer>(null));
    }

    @Test
    public void testSortNull() {
        assertThrows(IllegalArgumentException.class, () -> MinHeap.sort(null));
    }
}