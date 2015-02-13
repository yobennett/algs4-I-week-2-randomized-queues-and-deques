import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<E> implements Iterable<E> {

    private int size;
    private Node<E> first;
    private Node<E> last;

    // construct an empty deque
    public Deque() {
        this.size = 0;
    }

    private class Node<E> {
        E item;
        Node<E> next;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return !(size() > 0);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(E e) throws NullPointerException {
        if (e == null) {
            throw new NullPointerException("Cannot add null item");
        }

        Node<E> node = new Node<E>();
        node.item = e;

        if (isEmpty()) {
            node.next = null;
            this.last = node;
        } else {
            node.next = this.first;
        }

        this.first = node;
        this.size += 1;
    }

    // add the item to the end
    public void addLast(E e) throws NullPointerException {
        if (e == null) {
            throw new NullPointerException("Cannot add null item");
        }

        Node<E> node = new Node<E>();
        node.item = e;
        node.next = null;

        if (isEmpty()) {
            this.first = node;
        } else {
            Node<E> currLast = this.last;
            currLast.next = node;
        }

        this.last = node;
        this.size += 1;
    }

    // remove and return the item from the front
    public E removeFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Dequeue is empty");
        }

        Node<E> currFirst = this.first;

        this.first = currFirst.next;
        this.size -= 1;

        if (size() == 1) {
            this.last = this.first;
        }

        return currFirst.item;
    }

    // remove and return the item from the end
    public E removeLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Dequeue is empty");
        }

        Node<E> currLast = this.last;
        Node<E> node = null;
        Iterator<Node<E>> iterator = nodeIterator();
        while (iterator.hasNext()) {
            node = iterator.next();
            if (node.next == currLast) {
                break;
            }
        }
        this.last = node;

        this.size -= 1;

        if (size() == 1) {
            this.first = this.last;
        }

        return currLast.item;
    }

    // return an iterator over items in order from front to end
    public Iterator<E> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<E> {

        public boolean hasNext() {
            return false;
        }

        public void remove() {}

        public E next() {
            return null;
        }

    }

    // return an iterator over items in order from front to end
    public Iterator<Node<E>> nodeIterator() {
        return new DequeNodeIterator();
    }

    private class DequeNodeIterator implements Iterator<Node<E>> {

        Node<E> currNode = first;

        public boolean hasNext() {
            return currNode != null;
        }

        public void remove() {}

        public Node<E> next() {
            Node<E> node = currNode;
            currNode = currNode.next;
            return node;
        }

    }

    // unit testing
    public static void main(String[] args) {

        // FIFO
        int[] test1 = new int[10];
        int[] expected1 = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Deque<Integer> deque1 = new Deque<Integer>();
        for (int i = 0; i < 10; i++) {
            deque1.addFirst(i);
        }
        for (int j = 0; j < 10; j++) {
            test1[j] = deque1.removeLast();
        }
        if (!Arrays.equals(test1, expected1)) {
            System.out.println("test1: " + Arrays.toString(test1));
            System.out.println("expected1: " + Arrays.toString(expected1));
            throw new AssertionError("Does not match expected FIFO output");
        } else {
            System.out.println("Passed FIFO");
        }

        // LIFO
        int[] test2 = new int[10];
        int[] expected2 = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        Deque<Integer> deque2 = new Deque<Integer>();
        for (int i = 0; i < 10; i++) {
            deque2.addFirst(i);
        }
        for (int j = 0; j < 10; j++) {
            test2[j] = deque2.removeFirst();
        }
        if (!Arrays.equals(test2, expected2)) {
            System.out.println("test2: " + Arrays.toString(test2));
            System.out.println("expected2: " + Arrays.toString(expected2));
            throw new AssertionError("Does not match expected LIFO output");
        } else {
            System.out.println("Passed LIFO");
        }

    }
}
