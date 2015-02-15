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
            return this.currNode != null;
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

        /*
        Deque<Integer> deque = new Deque<Integer>();
        for (int i = 0; i < 1000; i++) {
            deque.addFirst(i);
        }

        int count = 0;
        Iterator<Integer> iterator = deque.iterator();
        while(iterator.hasNext()) {
            iterator.next();
            count++;
        }

        return;
        */
    }
}
