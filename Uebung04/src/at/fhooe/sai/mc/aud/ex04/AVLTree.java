package at.fhooe.sai.mc.aud.ex04;

public class AVLTree<T extends Comparable<T>> {
    protected AVLNode<T> root;
    protected int size;
    // Initializes the AVL-tree.
    public AVLTree() {

    }

    // Returns the number of elements stored in the AVL-tree.
    public int size() {

    }

    // Returns the height oft he tree in O(1).
    public int height() {

    }

    // Inserts the element elem into the AVL-tree (null elements are not allowed)
    public void insert(T elem) throws IllegalArgumentException {

    }

    // Returns the first element with key key, null if it was not found
    public T find(T key) throws IllegalArgumentException {

    }

    // Removes the first element with the key, returns true if it was found
    public boolean remove(T key) throws IllegalArgumentException {

    }

    // Returns an array representation of the stored elements
    // (Postorder-traversal).
    public Object[] toArray() {

    }


    // Helper method which returns the height oft he subtree n.
    int height(AVLNode<T> n) {

    }

    // ... additional private methods
    private void restructure(AVLNode<T> n) {

    }

    private boolean isAVLTree(AVLNode<T> n) {

    }

    private void updateHeights(AVLNode<T> n) {

    }
}
