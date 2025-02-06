//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        Main main = new Main();

//        int n = 5;
//        int x = 1;
//        int counter = 0;
//
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= i; j++) {
//                x += x;
//                counter++;
//            }
//        }

        System.out.println(main.binSearch(0, arr.length - 1, 11, arr));
    }

    public int binSearch(int low, int high, int val, int[] arr) {


        if (high < low) {
            return -1;
        }

        int i = (low + high) / 2;

        if (val == arr[i]) {
            return i;
        } else if (val < arr[i]) {
            return binSearch(low, i - 1, val, arr);
        } else {
            return binSearch(i + 1, high, val, arr);
        }
    }
}