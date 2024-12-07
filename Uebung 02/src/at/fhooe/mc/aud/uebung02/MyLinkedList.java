package at.fhooe.mc.aud.uebung02;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {

    MyListNode<T> head;
    MyListNode<T> tail;

    int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(T elem) throws IllegalArgumentException {
        MyListNode<T> n = new MyListNode<T>(elem);

        if (n.getElement() == null) {
            throw new IllegalArgumentException();
        }

        if (head == null) {
            head = tail = n;
            size++;
        } else {
            n.setNext(head);
            head = n;
            size++;
        }
    }

    @Override
    public void addLast(T elem) throws IllegalArgumentException {
        MyListNode<T> n = new MyListNode<T>(elem);

        if (n.getElement() == null) {
            throw new IllegalArgumentException();
        }

        if (tail == null) {
            head = tail = n;
            size++;
        } else {
            tail.setNext(n);
            tail = n;
            size++;
        }

    }

    @Override
    public void addSorted(T val) throws IllegalArgumentException {
        MyListNode<T> n = new MyListNode<T>(val);

        if (n.getElement() == null) {
            throw new IllegalArgumentException();
        }

        this.addFirst(val);
        this.sortASC();
    }

    @Override
    public void sortASC() {

        MyListNode<T> curr = head.getNext();
        MyListNode<T> prev = head;
        T swap;

        while (curr != null) {
            if (prev.getElement().compareTo(curr.getElement()) <= 0) {
                prev = curr;
                curr = curr.getNext();
            } else {
                swap = curr.getElement();
                curr.setElement(prev.getElement());
                prev.setElement(swap);
                curr = head.getNext();
                prev = head;
            }
        }
    }

    @Override
    public void sortDES() {
        MyListNode<T> curr = head.getNext();
        MyListNode<T> prev = head;
        T swap;

        while (curr != null) {
            if (prev.getElement().compareTo(curr.getElement()) >= 0) {
                prev = curr;
                curr = curr.getNext();
            } else {
                swap = curr.getElement();
                curr.setElement(prev.getElement());
                prev.setElement(swap);
                curr = head.getNext();
                prev = head;
            }
        }
    }


    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public T removeFirst() {
        MyListNode<T> curr;

        if (size == 0) {
            System.out.println("List empty!");
            return null;
        } else if (size == 1) {
            curr = head;
            clear();
            return curr.getElement();
        } else {
            curr = head;
            head = head.getNext();
            size--;
            return curr.getElement();
        }
    }

    @Override
    public T removeLast() {
        MyListNode<T> curr = head;
        MyListNode<T> prev = null;

        if (size == 0) {
            System.out.println("List empty!");
            return null;
        } else if (size == 1) {
            curr = head;
            clear();
            return curr.getElement();
        } else {
            while (curr.getNext() != null) {
                prev = curr;
                curr = curr.getNext();
            }
            prev.setNext(null);
            tail = prev;
            size--;
            return curr.getElement();
        }
    }

    @Override
    public T getFirst() {
        if (size == 0) {
            System.out.println("List empty!");
            return null;
        }
        return head.getElement();
    }

    @Override
    public T getLast() {
        if (size == 0) {
            System.out.println("List empty!");
            return null;
        }
        return tail.getElement();
    }

    @Override
    public boolean contains(T val) throws IllegalArgumentException {
        MyListNode<T> curr = head;

        if (val == null) {
            throw new IllegalArgumentException();
        } else if (size == 0) {
            System.out.println("List empty!");
            return false;
        } else if (val.compareTo(curr.getElement()) == 0) {
            return true;
        }

        while (curr != null) {
            if (val.compareTo(curr.getElement()) == 0) {
                return true;
            } else {
                curr = curr.getNext();
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        MyListNode<T> curr = head;

        if (this.size == 0 || this.size <= index || index < 0) {
            System.out.println("List empty or invalid index!");
            return null;
        } else if (this.size == index + 1) {
            return tail.getElement();
        } else {
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            return curr.getElement();
        }
    }

    @Override
    public T remove(int index) {
        MyListNode<T> curr = head;
        MyListNode<T> prev = null;

        if (this.size == 0 || index >= this.size || index < 0) {
            System.out.println("List empty or invalid index!");
            return null;
        } else if (this.size == 1) {
            this.clear();
            return curr.getElement();
        } else if (index == 0) {
            this.removeFirst();
        } else {
            for (int i = 0; i < index; i++) {
                prev = curr;
                curr = curr.getNext();
            }
            prev.setNext(curr.getNext());
            size--;
        }
        return curr.getElement();
    }

    @Override
    public Object[] toArray() {
        MyListNode<T> curr;

        if (size == 0) {
            System.out.println("List empty!");
            return null;
        } else {
            Object[] a = new Object[size];
            curr = head;
            for (int i = 0; i < a.length; i++) {
                a[i] = curr.getElement();
                curr = curr.getNext();
            }
            return a;
        }
    }

    public String toString() {
        if (size == 0) {
            return "List empty!";
        } else {
            MyListNode curr = head;
            StringBuilder builder = new StringBuilder();

            while (curr != null) {
                builder.append(curr.getElement()).append("; ");
                curr = curr.getNext();
            }

            return builder.toString();
        }
    }
}
