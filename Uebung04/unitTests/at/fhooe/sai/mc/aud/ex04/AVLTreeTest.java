package at.fhooe.sai.mc.aud.ex04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest {

    AVLTree<Integer> empty, tree1, tree2;

    @BeforeEach
    public void setUp() {

        empty = new AVLTree<Integer>();

        tree1 = new AVLTree<Integer>();
        tree1.insert(5);
        tree1.insert(3);
        tree1.insert(2);
        tree1.insert(4);
        tree1.insert(1);
        tree1.insert(7);
        tree1.insert(6);
        tree1.insert(8);
        tree1.insert(9);

        tree2 = new AVLTree<Integer>();
        tree2.insert(1);
        tree2.insert(2);
        tree2.insert(3);
        tree2.insert(4);
        tree2.insert(5);
        tree2.insert(6);
        tree2.insert(7);
        tree2.insert(8);
        tree2.insert(9);
    }

    @Test
    public void testSize() {
        assertEquals(0, empty.size());
        assertEquals(9, tree1.size());
        assertEquals(9, tree2.size());
    }

    @Test
    public void testTreeHeight() {
        assertEquals(0, empty.height());
        empty.insert(1);
        assertEquals(1, empty.height());
        assertEquals(4, tree1.height());
        assertEquals(5, tree2.height()); // Unbalanced tree should be rebalanced
    }

    @Test
    public void testInsert() {
        empty.insert(1);
        assertEquals(1, empty.size());
        empty.insert(2);
        assertEquals(2, empty.size());
        assertEquals(1, empty.find(1));
        assertEquals(2, empty.find(2));
    }

    @Test
    public void testFind() {
        assertEquals(9, tree1.find(9));
        assertEquals(1, tree1.find(1));
        assertNull(tree1.find(10));
        assertNull(empty.find(1));
    }

    @Test
    public void testRemoveLeaf() {
        assertTrue(tree1.remove(9));
        assertFalse(tree1.remove(10));
        empty.insert(1);
        assertTrue(empty.remove(1));
    }

    @Test
    public void testRemoveLeaf2() {
        assertTrue(tree1.remove(1));
        assertFalse(tree1.remove(10));
        empty.insert(1);
        assertTrue(empty.remove(1));
    }

    @Test
    public void testRemoveNodeWithOneChild() {
        assertTrue(tree1.remove(2));
        assertEquals(8, tree1.size());
    }

    @Test
    public void testRemoveNodeWithOneChild2() {
        assertTrue(tree1.remove(8));
        assertEquals(8, tree1.size());
    }

    @Test
    public void testRemoveNodeWithTwoChildren() {
        assertTrue(tree1.remove(3));
        assertEquals(8, tree1.size());
    }

    @Test
    public void testRemoveNodeWithTwoChildren2() {
        assertTrue(tree1.remove(7));
        assertEquals(8, tree1.size());
    }

    @Test
    public void testRemoveRoot() {
        assertTrue(tree1.remove(5));
        assertEquals(8, tree1.size());
    }

    @Test
    public void testRebalanceAfterInsert() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        assertEquals(2, tree.root.data); // Root should be rebalanced
        assertEquals(1, tree.root.left.data);
        assertEquals(3, tree.root.right.data);
    }

    @Test
    public void toArray() {
        Object[] expected = {1, 2, 4, 3, 6, 9, 8, 7, 5};
        assertArrayEquals(expected, tree1.toArray());
    }

    @Test
    public void testNodeHeight() {
        assertEquals(4, tree1.height());
        tree1.remove(9);
        assertEquals(4, tree1.height());
    }

    @Test
    public void testEmptyTree() {
        assertNull(empty.find(1));
        assertFalse(empty.remove(1));
        assertEquals(0, empty.size());
    }

    @Test
    public void testInsertNull() {
        assertThrows(IllegalArgumentException.class, () -> empty.insert(null));
    }

    @Test
    public void testRemoveNull() {
        assertThrows(IllegalArgumentException.class, () -> empty.remove(null));
    }

    @Test
    public void testFindNull() {
        assertThrows(IllegalArgumentException.class, () -> empty.find(null));
    }
}
