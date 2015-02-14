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
        Node<E> prev;
        Node<E> next;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
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
        node.prev = null;

        if (isEmpty()) {
            node.next = null;
            this.last = node;
        } else {
            Node<E> currFirst = this.first;
            currFirst.prev = node;
            node.next = currFirst;
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
        node.prev = null;
        node.next = null;

        if (isEmpty()) {
            this.first = node;
        } else {
            Node<E> currLast = this.last;
            currLast.next = node;
            node.prev = currLast;
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
        Node<E> newFirst = currFirst.next;
        if (newFirst != null) {
            newFirst.prev = null;
        }
        this.first = newFirst;

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
        Node<E> newLast = currLast.prev;
        if (newLast != null) {
            newLast.next = null;
        }
        this.last = newLast;

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

        Node<E> currNode;

        public DequeIterator() {
            currNode = first;
        }

        public boolean hasNext() {
            return currNode.next != null;
        }

        public void remove() {}

        public E next() {
            Node<E> node = currNode;
            currNode = currNode.next;
            return node.item;
        }

    }

    // unit testing
    public static void main(String[] args) {

        Deque<Integer> deque = new Deque<Integer>();

        deque.addFirst(0);
        if (deque.removeFirst() != 0) {
            throw new AssertionError("Expected " + 0);
        }

        deque.addFirst(1);
        if (deque.removeLast() != 1) {
            throw new AssertionError("Expected " + 1);
        }

        deque.addLast(2);
        if (deque.removeFirst() != 2) {
            throw new AssertionError("Expected " + 2);
        }

        deque.addLast(3);
        if (deque.removeLast() != 3) {
            throw new AssertionError("Expected " + 3);
        }

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
            throw new AssertionError("Expected " + Arrays.toString(expected1) + " but got " + Arrays.toString(test1));
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
            throw new AssertionError("Expected " + Arrays.toString(expected2) + " but got " + Arrays.toString(test2));
        }

    }
}
