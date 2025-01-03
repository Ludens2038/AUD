package at.fhooe.sai.mc.aud.ex04;

public class AVLTree<T extends Comparable<T>> {

    protected AVLNode<T> root;
    protected int size;

    // Initializes the AVL-tree.
    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    //private class for recursive traversal
    private class Index {
        int index = 0;
    }

    // Returns the number of elements stored in the AVL-tree.
    public int size() {
        return this.size;
    }

    // Returns the height of the tree in O(1).
    public int height() {
        if (this.root == null) {
            return 0;
        }
        return this.root.height;
    }

    // Inserts the element elem into the AVL-tree (null elements are not allowed)
    public void insert(T elem) throws IllegalArgumentException {

        //check if element is null
        if (elem == null) {
            throw new IllegalArgumentException("Null elements are not allowed.");
        }

        AVLNode<T> n = new AVLNode<T>(elem);

        if (root == null) {
            n.height = 1;
            root = n;
            size++;
            return;
        }

        AVLNode<T> child = root;
        AVLNode<T> parent = null;

        //find the correct position for the new node
        while (child != null) {
            parent = child;
            if (n.data.compareTo(child.data) < 0) {
                child = child.left;
            } else {
                child = child.right;
            }
        }

        //insert the new node
        if (n.data.compareTo(parent.data) < 0) {
            parent.left = n;
        } else {
            parent.right = n;
        }
        n.height = 1;
        n.parent = parent;
        size++;

        //update the heights and restructure the tree if necessary
        updateHeights(parent);

        if (!isAVLTree(root)) {
            restructure(root);
        }
    }

    // Returns the first element with key key, null if it was not found
    public T find(T key) throws IllegalArgumentException {

        //check if key is null
        if (key == null) {
            throw new IllegalArgumentException("Null keys are not allowed.");
        }

        AVLNode<T> p = root;

        //find the element with the key
        while (p != null) {
            if (key.compareTo(p.data) == 0) {
                return p.data;
            } else if (key.compareTo(p.data) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return null;
    }

    // Removes the first element with the key, returns true if it was found
    public boolean remove(T key) throws IllegalArgumentException {
        //check if key is null
        if (key == null) {
            throw new IllegalArgumentException("Null keys are not allowed.");
        }

        //check if the element is in the tree
        if (find(key) == null) {
            return false;
        }

        AVLNode<T> curr = root;
        AVLNode<T> parent = null;

        //find the element with the key
        while (curr != null) {
            if (key.compareTo(curr.data) == 0) {
                break;
            } else if (key.compareTo(curr.data) < 0) {
                parent = curr;
                curr = curr.left;
            } else {
                parent = curr;
                curr = curr.right;
            }
        }

        if (curr.left == null && curr.right == null) { //case 1: no children
            if (parent == null) {
                root = null;
            } else if (curr.data.compareTo(parent.data) < 0) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (curr.left == null || curr.right == null) { //case 2: one child
            AVLNode<T> child;
            if (curr.left == null) { //if the child is on the right
                child = curr.right;
            } else {
                child = curr.left;
            }
            if (parent == null) { //if the node to be removed is the root
                root = child;
            } else if (curr.data.compareTo(parent.data) < 0) {  //if the node to be removed is on the left
                parent.left = child;
            } else {
                parent.right = child;
            }
            child.parent = parent;
        } else { //case 3: two children
            AVLNode<T> offspring = curr.right;
            AVLNode<T> offspringParent = curr;
            while (offspring.left != null) { //find the leftmost child of the right subtree
                offspringParent = offspring;
                offspring = offspring.left;
            }
            if (offspringParent != curr) { //if the leftmost child of the right subtree is not the right child of the node to be removed
                offspringParent.left = offspring.right;
                if (offspring.right != null) offspring.right.parent = offspringParent; //update the parent of the right child of the leftmost child of the right subtree
                offspring.right = curr.right;
            }
            offspring.left = curr.left;
            if (curr.left != null) curr.left.parent = offspring; //update the parent of the left child of the node to be removed

            if (parent == null) { //if the node to be removed is the root
                root = offspring;
            } else if (curr.data.compareTo(parent.data) < 0) { //if the node to be removed is on the left
                parent.left = offspring;
            } else { //if the node to be removed is on the right
                parent.right = offspring;
            }
            offspring.parent = parent;
        }

        size--;
        if (!isAVLTree(root)) { //restructure the tree if necessary
            restructure(root);
        }

        return true;
    }

    // Returns an array representation of the stored elements (Postorder-traversal).
    public Object[] toArray() {
        @SuppressWarnings("unchecked") // safe cast
        T[] postOrder = (T[]) new Comparable[this.size];
        Index i = new Index();
        traversePostOrder(root, postOrder, i);
        return postOrder;
    }

    private void traversePostOrder(AVLNode<T> node, Object[] array, Index i) {
        if (node == null) {
            return;
        }

        traversePostOrder(node.left, array, i); //recursive call for the left child
        traversePostOrder(node.right, array, i); //recursive call for the right child
        array[i.index] = node.data; //store the element in the array
        i.index++;
    }

    int height(AVLNode<T> n) {
        if (n == null) {
            return 0;
        }
        return n.height;
    }

    private void restructure(AVLNode<T> n) {
        while (n != null) { //restructure the tree from the bottom to the top
            int balanceFactor = height(n.left) - height(n.right); //calculate the balance factor of the current node n (height of the left subtree - height of the right subtree)
            if (balanceFactor > 1) { //if the balance factor is greater than 1, the tree is left-heavy
                if (height(n.left.left) >= height(n.left.right)) { //if the left subtree is left-heavy
                    rotateRight(n); //rotate right
                } else {
                    rotateLeft(n.left); //rotate left
                    rotateRight(n); //rotate right
                }
            } else if (balanceFactor < -1) { //if the balance factor is less than -1, the tree is right-heavy
                if (height(n.right.right) >= height(n.right.left)) { //if the right subtree is right-heavy
                    rotateLeft(n); //rotate left
                } else { //if the right subtree is left-heavy
                    rotateRight(n.right); //rotate right
                    rotateLeft(n); //rotate left
                }
            }
            updateHeights(n); //update the heights of the nodes
            n = n.parent;
        }
    }

    private void rotateLeft(AVLNode<T> n) {
        AVLNode<T> newRoot = n.right; //new root is the right child of the current node n
        n.right = newRoot.left; //the left child of the new root becomes
        if (newRoot.left != null) newRoot.left.parent = n; //update the parent of the left child of the new root
        newRoot.parent = n.parent; //update the parent of the new root
        if (n.parent == null) root = newRoot; //if the current node n is the root, the new root becomes the root
        else if (n == n.parent.left) n.parent.left = newRoot; //if the current node n is the left child of its parent, the new root becomes the left child of the parent
        else n.parent.right = newRoot; //if the current node n is the right child of its parent, the new root becomes
        newRoot.left = n; //the current node n becomes the left child of the new root
        n.parent = newRoot; //the new root becomes the parent of the current node n
        updateHeights(n); //update the heights of the nodes
    }

    private void rotateRight(AVLNode<T> n) {
        AVLNode<T> newRoot = n.left; //new root is the left child of the current node n
        n.left = newRoot.right; //the right child of the new root becomes
        if (newRoot.right != null) newRoot.right.parent = n; //update the parent of the right child of the new root
        newRoot.parent = n.parent; //update the parent of the new root
        if (n.parent == null) root = newRoot; //if the current node n is the root, the new root becomes the root
        else if (n == n.parent.right) n.parent.right = newRoot; //if the current node n is the right child of its parent, the new root becomes
        else n.parent.left = newRoot; //if the current node n is the left child of its parent, the new root becomes the left child of the parent
        newRoot.right = n; //the current node n becomes the right child of the new root
        n.parent = newRoot; //the new root becomes the parent of the current node n
        updateHeights(n); //update the heights of the nodes
    }

    private boolean isAVLTree(AVLNode<T> n) {
        if (n == null) {
            return true;
        }

        int leftHeight = height(n.left); //height of the left subtree
        int rightHeight = height(n.right); //height of the right subtree
        int balanceFactor = leftHeight - rightHeight; //balance factor of the current node n
        if (balanceFactor < 0) balanceFactor = -balanceFactor; //absolute value of the balance factor

        if (balanceFactor > 1) { //if the balance factor is greater than 1, the tree is not balanced
            return false;
        }

        return isAVLTree(n.left) && isAVLTree(n.right); //recursive call for the left and right child
    }

    private void updateHeights(AVLNode<T> n) {
        while (n != null) {
            int leftHeight = height(n.left); //height of the left subtree
            int rightHeight = height(n.right); //height of the right subtree
            if (leftHeight > rightHeight) { //update the height of the current node n
                n.height = leftHeight + 1;
            } else { //update the height of the current node n
                n.height = rightHeight + 1;
            }
            n = n.parent;
        }
    }
}
