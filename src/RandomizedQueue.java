import java.util.Iterator;

public class RandomizedQueue<E> implements Iterable<E> {

    private int size;

    public RandomizedQueue() {
        this.size = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the queue
    public int size() {
        return this.size;
    }

    // add the item
    public void enqueue(E e) {
        this.size += 1;
    }

    // remove and return a random item
    public E dequeue() {

        this.size -= 1;

        return null;
    }

    // return (but do not remove) a random item
    public E sample() {
        return null;
    }

    // return an independent iterator over items in random order
    public Iterator<E> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<E> {

        public RandomizedQueueIterator() {
        }

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
