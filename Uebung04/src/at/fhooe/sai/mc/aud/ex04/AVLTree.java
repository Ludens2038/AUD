package at.fhooe.sai.mc.aud.ex04;

public class AVLTree<T extends Comparable<T>> {

    protected AVLNode<T> root;
    protected int size;

    // Initializes the AVL-tree.
    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    private class Index {
        int index = 0;
    }

    // Returns the number of elements stored in the AVL-tree.
    public int size() {
        return this.size;
    }

    // Returns the height oft he tree in O(1).
    public int height() {
        return this.root.height;
    }

    // Inserts the element elem into the AVL-tree (null elements are not allowed)
    public void insert(T elem) throws IllegalArgumentException {

        if (elem == null) {
            throw new IllegalArgumentException();
        }

        AVLNode<T> n = new AVLNode<T>(elem);

        if (root == null) {
            root = n;
            size++;
        }

        AVLNode<T> child = root;
        AVLNode<T> parent = null;

        while (child != null) {
            child.parent = parent;
            parent = child;
            if (n.data.compareTo(child.data) > 0) {
                child = child.left;
            } else {
                child = child.right;
            }
        }

        if (n.data.compareTo(parent.data) < 0) {
            parent.left = n;
            size++;
        } else {
            parent.right = n;
            size++;
        }

    }

    // Returns the first element with key key, null if it was not found
    public T find(T key) throws IllegalArgumentException {

        if (key == null) {
            throw new IllegalArgumentException();
        }

        AVLNode<T> p = root;

        if (root == null) {
            return null;
        } else {
            while (p != null) {
                if (key.compareTo(p.data) == 0) {
                    return (T)p.data;
                } else if (key.compareTo(p.data) < 0) {
                    p = p.left;
                } else {
                    p = p.right;
                }
            }
            return null;
        }
    }

    // Removes the first element with the key, returns true if it was found
    public boolean remove(T key) throws IllegalArgumentException {

        if (this.find(key) == null) {
            return false;

        } else {

            AVLNode<T> n = this.root;
            AVLNode<T> parent = null;

            while (n != null) {
                if (key == n.data) {
                    break;
                } else if (key.compareTo(n.data) < 0) {
                    parent = n;
                    n = n.left;
                } else if (key.compareTo(n.data) > 0) {
                    parent = n;
                    n = n.right;
                }
            }

            // in case node to remove is leaf
            if (n.left == null && n.right == null) {
                // in case only root is in tree
                if (parent == null) {
                    this.root = null;
                    // based on value comparison set parent left or right to null
                } else if (n.data.compareTo(parent.data) < 0) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }

                // in case node to remove has one child
            } else if (n.left == null || n.right == null) {
                // node to store child information
                AVLNode<T> child = null;
                if (n.left == null) {
                    child = n.right;
                } else {
                    child = n.left;
                }
                // in case parent of child is root
                if (parent == null) {
                    this.root = child;
                } else if (n.data.compareTo(parent.data) < 0) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }

                // in case node to remove has two children
            } else if (n.left != null && n.right != null) {
                // traverse tree to find node with nearest, highest value
                AVLNode<T> offspring = n.right;
                AVLNode<T> offspringParent = n;
                while (offspring.left != null) {
                    offspringParent = offspring;
                    offspring = offspring.left;
                }
                // if parent of offspring is node to remove
                if (offspringParent != n) {
                    offspringParent.left = offspring.right;
                    offspring.right = n.right;
                }
                offspring.left = n.left;
                // if root is n
                if (parent == null) {
                    root = offspring;
                    // if not rearranging position of offspring
                } else if (n.data.compareTo(parent.data) < 0) {
                    parent.left = offspring;
                } else {
                    parent.right = offspring;
                }
            }
            size--;
            return true;
        }
    }

    // Returns an array representation of the stored elements
    // (Postorder-traversal).
    public Object[] toArray() {
        Object[] postOrder = (T[]) new Comparable[this.size];
        // Index created to track position where to insert from node to array
        Index i = new Index();
        // call helper method
        traversePostOrder(root, postOrder, i);
        return postOrder;
    }

    private void traversePostOrder(AVLNode node, Object[] array, Index i) {

        if (node == null) {
            return;
        }

        traversePostOrder(node.left, array, i);
        traversePostOrder(node.right, array, i);
        array[i.index] = node.data;
        i.index++;
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
