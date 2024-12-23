package at.fhooe.sail.mc.aud.ex03.task3;

public class QuickSort {

    public void quicksortMot(int[] array, int size, int left, int right) {

        if (array.length == 0) {
            System.out.println("Array is empty.");
            return;
        }

        if (right > left) {
            int pivot = medianOfThree(array, left, right);

            int i = left;
            int j = right;

            while (i <= j) {
                while (array[i] < pivot) {
                    i++;
                }
                while (array[j] > pivot) {
                    j--;
                }
                if (i <= j) {
                    swap(array, i, j);
                    i++;
                    j--;
                }
            }

            quicksortMot(array, j + 1, left, j);
            quicksortMot(array, right - i, i, right);
        }
    }

    private int medianOfThree(int[] array, int left, int right) {
        int mid = (left + right) / 2;

        if (array[left] > array[mid]) {
            swap(array, left, mid);
        }
        if (array[left] > array[right]) {
            swap(array, left, right);
        }
        if (array[mid] > array[right]) {
            swap(array, mid, right);
        }

        return array[mid];
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}