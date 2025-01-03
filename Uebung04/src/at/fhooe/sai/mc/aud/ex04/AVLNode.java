package at.fhooe.sai.mc.aud.ex04;

public class AVLNode<T extends Comparable<T>> {

    public AVLNode<T> parent = null; // Pointer to the parent node
    public AVLNode<T> left = null; // Pointer to the left child-node
    public AVLNode<T> right = null; // Pointer to the right vhild-node
    public T data; // Content oft he node
    public int height = 0; // Tob e able to calculate the height in O(1)


    public AVLNode(T elem) {
        this.data = elem;
        this.parent = null;
        this.left = null;
        this.right = null;
        this.height = 0;
    }
}
