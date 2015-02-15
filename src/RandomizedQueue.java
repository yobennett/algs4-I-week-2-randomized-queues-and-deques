import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<E> implements Iterable<E> {

    private E[] q;
    private int capacity;
    private int size;

    public RandomizedQueue() {
        capacity = 1;
        q = (E[]) new Object[capacity]; // ugly cast
        size = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    private void resize(int newCapacity) {
        //System.out.println("\nresizing from " + capacity + " to " + newCapacity + "\n");
        E[] copy = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            copy[i] = q[i];
        }
        q = copy;
        capacity = newCapacity;
    }

    // add the item
    public void enqueue(E e) {
        if (e == null) {
            throw new NullPointerException("Cannot add null item");
        }

        // resize if not enough capacity
        if (size() + 1 > capacity) {
            resize(2 * capacity);
        }

        q[size] = e;
        size += 1;
    }

    // remove and return a random item
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("RandomizedQueue is empty");
        }

        // randon sample, swap sample item and last item, reduce size
        RandomSample sample = new RandomSample();

        size -= 1;
        q[sample.index] = q[size];
        q[size] = null;

        // resize down to 1/4 capacity
        if (!isEmpty() && (size() <= capacity / 4)) {
            resize(capacity / 2);
        }

        return sample.value;
    }

    private class RandomSample {

        public int index;
        public E value;

        public RandomSample() {
            this.index = StdRandom.uniform(size);;
            this.value = q[index];
        }

    }

    // return (but do not remove) a random item
    public E sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("RandomizedQueue is empty");
        }

        RandomSample sample = new RandomSample();
        return sample.value;
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

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public E next() {
            if (isEmpty()) {
                throw new NoSuchElementException("RandomizedQueue is empty");
            }

            return null;
        }

    }

    public String toString() {
        return "size=" + size() + ", capacity=" + capacity;
    }

    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();

        // one element enqueue and dequeue
        q.enqueue(100);
        q.dequeue();

        for (int i = 0; i < 100; i++) {
            q.enqueue(i);
            System.out.println("After enqueue " + i + " " + q.toString());
        }

        for (int j = 0; j < 100; j++) {
            System.out.println("Deque: " + q.dequeue() + " " + q.toString());
        }
        System.out.println("After dequeue: " + q.toString());
    }

}
