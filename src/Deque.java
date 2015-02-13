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
        if (!isEmpty()) {
            return this.first.item;
        } else {
            throw new NoSuchElementException("Dequeue is empty");
        }
    }

    // remove and return the item from the end
    public E removeLast() throws NoSuchElementException {
        if (!isEmpty()) {
            return this.last.item;
        } else {
            throw new NoSuchElementException("Dequeue is empty");
        }
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
        return new DequeNodeIterator(first);
    }

    private class DequeNodeIterator implements Iterator<Node<E>> {

        Node<E> currNode;

        public DequeNodeIterator(Node<E> firstNode) {
            this.currNode = firstNode;
        }

        public boolean hasNext() {
            return currNode.next != null;
        }

        public void remove() {}

        public Node<E> next() {
            return currNode.next;
        }

    }

    // unit testing
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("foo");
        deque.addFirst("bar");
        deque.addLast("baz");
    }
}
