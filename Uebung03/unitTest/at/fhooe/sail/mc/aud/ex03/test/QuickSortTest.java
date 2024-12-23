package at.fhooe.sail.mc.aud.ex03.test;

import at.fhooe.sail.mc.aud.ex03.task3.QuickSort;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {

    @Test
    public void testEmptyArray() {
        QuickSort sorter = new QuickSort();
        int[] array = {};

        sorter.quicksortMot(array, array.length, 0, array.length - 1);

        assertArrayEquals(new int[]{}, array, "Empty array should remain unchanged.");
    }

    @Test
    public void testSingleElementArray() {
        QuickSort sorter = new QuickSort();
        int[] array = {42};

        sorter.quicksortMot(array, array.length, 0, array.length - 1);

        assertArrayEquals(new int[]{42}, array, "Single-element array should remain unchanged.");
    }

    @Test
    public void testSortedArray() {
        QuickSort sorter = new QuickSort();
        int[] array = {1, 2, 3, 4, 5};

        sorter.quicksortMot(array, array.length, 0, array.length - 1);

        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array, "Sorted array should remain sorted.");
    }

    @Test
    public void testReverseSortedArray() {
        QuickSort sorter = new QuickSort();
        int[] array = {5, 4, 3, 2, 1};

        sorter.quicksortMot(array, array.length, 0, array.length - 1);

        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array, "Reverse-sorted array should be sorted in ascending order.");
    }

    @Test
    public void testUnsortedArray() {
        QuickSort sorter = new QuickSort();
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5};

        sorter.quicksortMot(array, array.length, 0, array.length - 1);

        assertArrayEquals(new int[]{1, 1, 2, 3, 4, 5, 5, 6, 9}, array, "Unsorted array should be sorted in ascending order.");
    }

    @Test
    public void testArrayWithDuplicates() {
        QuickSort sorter = new QuickSort();
        int[] array = {4, 2, 4, 2, 4, 2, 4};

        sorter.quicksortMot(array, array.length, 0, array.length - 1);

        assertArrayEquals(new int[]{2, 2, 2, 4, 4, 4, 4}, array, "Array with duplicates should be sorted correctly.");
    }

    @Test
    public void testNegativeNumbers() {
        QuickSort sorter = new QuickSort();
        int[] array = {-3, -1, -4, -2, -5};

        sorter.quicksortMot(array, array.length, 0, array.length - 1);

        assertArrayEquals(new int[]{-5, -4, -3, -2, -1}, array, "Array with negative numbers should be sorted correctly.");
    }

    @Test
    public void testMixedPositiveAndNegativeNumbers() {
        QuickSort sorter = new QuickSort();
        int[] array = {-3, 1, -4, 2, -5, 0};

        sorter.quicksortMot(array, array.length, 0, array.length - 1);

        assertArrayEquals(new int[]{-5, -4, -3, 0, 1, 2}, array, "Array with mixed positive and negative numbers should be sorted correctly.");
    }
}
