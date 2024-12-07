package at.fhooe.mc.aud.uebung02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class MyLinkedListTest {

    MyLinkedList<Integer> l = new MyLinkedList<Integer>();
    MyLinkedList<Integer> e = new MyLinkedList<Integer>();

    @BeforeEach
    void setUp() {
        l.addFirst(10);
        l.addLast(1);
        l.addFirst(13);
        l.addLast(3);
        l.addLast(11);
        l.addLast(5);
        l.addLast(4);
        l.addLast(7);
    }

    @Test
    void testSize() {
        assertEquals(8, l.size());
        assertEquals(0, e.size());
    }

    @Test
    void testAddFirst() {
        l.addFirst(12);
        assertEquals(9, l.size());
        assertEquals(12, l.getFirst());

        e.addFirst(10);
        e.addFirst(20);
        e.addFirst(30);
        assertEquals(3, e.size());
        assertEquals(30, e.getFirst());

        ;
    }

    @Test
    void testAddLast() {
        l.addLast(12);
        assertEquals(9, l.size());
        assertEquals(12, l.getLast());

        e.addLast(10);
        e.addLast(20);
        e.addLast(30);
        assertEquals(3, e.size());
        assertEquals(30, e.getLast());
    }

    @Test
    void testAddSorted() {
        l.addSorted(73);
        assertEquals(9, l.size());
        assertEquals(73, l.getLast());

        e.addSorted(10);
        e.addSorted(20);
        e.addSorted(30);
        assertEquals(3, e.size());
        assertEquals(30, e.getLast());
        assertEquals(10, e.getFirst());
    }

    @Test
    void testSortASC() {
        l.sortASC();
        assertEquals(1, l.getFirst());
        assertEquals(13, l.getLast());
    }

    @Test
    void testSortDES() {
        l.sortDES();
        assertEquals(13, l.getFirst());
        assertEquals(1, l.getLast());
    }

    @Test
    void testClear() {
        l.clear();
        assertEquals(0, l.size());

        e.clear();
        assertEquals(0, e.size());
    }

    @Test
    void testRemoveFirst() {
        l.removeFirst();
        assertEquals(7, l.size());
        assertEquals(10, l.getFirst());

        assertEquals(null, e.removeFirst());
        assertEquals(0, e.size());

        e.addFirst(1);
        assertEquals(1, e.removeFirst());
        assertEquals(0, e.size());
    }

    @Test
    void testRemoveLast() {
        l.removeLast();
        assertEquals(7, l.size());
        assertEquals(4, l.getLast());

        assertEquals(null, e.removeLast());
        assertEquals(0, e.size());

        e.addLast(1);
        assertEquals(1, e.removeLast());
        assertEquals(0, e.size());
    }

    @Test
    void testGetFirst() {
        assertEquals(13, l.getFirst());

        assertEquals(null, e.getFirst());
    }

    @Test
    void testGetLast() {
        assertEquals(7, l.getLast());

        assertEquals(null, e.getLast());
    }

    @Test
    void testContains() {
        assertTrue(l.contains(5));
        assertFalse(l.contains(12));

        assertFalse(e.contains(5));

        e.addLast(5);
        assertTrue(e.contains(5));
    }

    @Test
    void testGet() {
        assertEquals(13, l.get(0));
        assertEquals(7, l.get(7));
        assertEquals(5, l.get(5));
        assertEquals(null, l.get(8));
        assertEquals(null, l.get(-1));

        assertEquals(null, e.get(0));
    }

    @Test
    void testRemove() {
        assertEquals(7, l.remove(7));
        assertEquals(3, l.remove(3));
        assertEquals(6, l.size());
        assertEquals(7, l.getLast());
        assertEquals(13, l.remove(0));
        assertEquals(null, l.remove(8));
        assertEquals(null, l.remove(-1));

        assertEquals(null, e.remove(0));

        e.addLast(5);
        assertEquals(5, e.remove(0));
    }

    @Test
    void testToArray() {
        Object[] a = l.toArray();
        assertEquals(13, a[0]);
        assertEquals(7, a[7]);

        Object[] b;
        assertNull(b = e.toArray());
    }

    @Test
    void testToString() {
        assertEquals("13; 10; 1; 3; 11; 5; 4; 7; ", l.toString());

        assertEquals("List empty!", e.toString());
    }

    @Test
    void testMyListNode() {
        MyListNode n = new MyListNode();
    }

    @Test
    public void testAddNullException() {
        assertThrows(IllegalArgumentException.class, () -> l.addFirst(null));
        assertThrows(IllegalArgumentException.class, () -> l.addLast(null));
        assertThrows(IllegalArgumentException.class, () -> l.addSorted(null));
        assertThrows(IllegalArgumentException.class, () -> l.contains(null));
    }
}