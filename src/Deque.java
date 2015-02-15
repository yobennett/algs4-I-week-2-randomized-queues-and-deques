import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node<Item> first;
    private Node<Item> last;

    // construct an empty deque
    public Deque() {
        this.size = 0;
    }

    private class Node<Item> {
        private Item item;
        private Node<Item> prev;
        private Node<Item> next;

        public Node() {
            this.item = null;
            this.prev = null;
            this.next = null;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item i) {
            this.item = i;
        }

        public Node<Item> getPrev() {
            return prev;
        }

        public void setPrev(Node<Item> p) {
            this.prev = p;
        }

        public Node<Item> getNext() {
            return next;
        }

        public void setNext(Node<Item> n) {
            this.next = n;
        }
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
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot add null item");
        }

        Node<Item> node = new Node<Item>();
        node.setItem(item);
        node.setPrev(null);

        if (isEmpty()) {
            node.setNext(null);
            this.last = node;
        } else {
            Node<Item> currFirst = this.first;
            currFirst.setPrev(node);
            node.setNext(currFirst);
        }

        this.first = node;
        this.size += 1;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot add null item");
        }

        Node<Item> node = new Node<Item>();
        node.setItem(item);
        node.setPrev(null);
        node.setNext(null);

        if (isEmpty()) {
            this.first = node;
        } else {
            Node<Item> currLast = this.last;
            currLast.setNext(node);
            node.setPrev(currLast);
        }

        this.last = node;
        this.size += 1;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Dequeue is empty");
        }

        Node<Item> currFirst = this.first;
        Node<Item> newFirst = currFirst.getNext();
        if (newFirst != null) {
            newFirst.setPrev(null);
        }
        this.first = newFirst;

        this.size -= 1;

        if (size() == 1) {
            this.last = this.first;
        }

        return currFirst.getItem();
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Dequeue is empty");
        }

        Node<Item> currLast = this.last;
        Node<Item> newLast = currLast.getPrev();
        if (newLast != null) {
            newLast.setNext(null);
        }
        this.last = newLast;

        this.size -= 1;

        if (size() == 1) {
            this.first = this.last;
        }

        return currLast.getItem();
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node<Item> currNode;

        public DequeIterator() {
            this.currNode = first;
        }

        public boolean hasNext() {
            if (this.currNode != null) {
                return this.currNode.getNext() != null;
            } else {
                return false;
            }
        }

        public void remove() {
            throw new NoSuchElementException();
        }

        public Item next() {
            Node<Item> node = this.currNode;
            this.currNode = this.currNode.getNext();
            return node.getItem();
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
            System.out.println("deque " + j);
            test1[j] = deque1.removeLast();
        }
        if (!Arrays.equals(test1, expected1)) {
            throw new AssertionError("Expected "
                    + Arrays.toString(expected1)
                    + " but got " + Arrays.toString(test1));
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
            throw new AssertionError("Expected "
                    + Arrays.toString(expected2)
                    + " but got " + Arrays.toString(test2));
        }

    }
}
