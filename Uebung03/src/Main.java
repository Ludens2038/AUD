
import at.fhooe.sail.mc.aud.ex03.task1.MinHeap;

public class Main {

    public static void main(String[] args) {
        MinHeap<Integer> h = new MinHeap<Integer>(7);

        h.insert(1);
        h.insert(2);
        h.insert(3);
        h.insert(6);
        h.insert(9);
        h.insert(11);
        h.insert(16);

        Object[] a = h.toArray();

        for (int i = 0; i < a.length; i++) {
            System.out.println("[" + a[i] + "]; ");
        }
        System.out.println("size: " + h.size());

        System.out.println();

        h.removeMin();

        Object[] b = h.toArray();

        for (int i = 0; i < b.length; i++) {
            System.out.println("[" + b[i] + "]; ");
        }
        System.out.println("size: " + h.size());
    }
}