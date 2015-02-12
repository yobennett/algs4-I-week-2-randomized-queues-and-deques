import java.util.Iterator;

public class Deque<E> implements Iterable<E> {

    // construct an empty deque
    public Deque() {}

    // is the deque empty?
    public boolean isEmpty() {
        return false;
    }

    // return the number of items on the deque
    public int size() {
        return 0;
    }

    // add the item to the front
    public void addFirst(E e) {}

    // add the item to the end
    public void addLast(E e) {}

    // remove and return the item from the front
    public E removeFirst() {
        return null;
    }

    // remove and return the item from the end
    public E removeLast() {
        return null;
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

    // unit testing
    public static void main(String[] args) {}
}
