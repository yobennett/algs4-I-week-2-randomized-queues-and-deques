import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<E> implements Iterable<E> {

    private E[] q;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
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

        // resize down to 1/4 capacity (or 1 if empty)
        if (!isEmpty() && (size() <= capacity / 4)) {
            resize(capacity / 2);
        } else if (isEmpty()) {
            resize(1);
        }

        return sample.value;
    }

    private class RandomSample {

        public int index;
        public E value;

        public RandomSample() {
            this.index = StdRandom.uniform(size);
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
        return new RandomizedQueueIterator(size);
    }

    private class RandomizedQueueIterator implements Iterator<E> {

        private int[] indexes;
        private int index;
        private int size;

        public RandomizedQueueIterator(int size) {
            this.size = size;
            indexes = new int[this.size];
            for (int i = 0; i < this.size; i++) {
                indexes[i] = i;
            }
            StdRandom.shuffle(indexes);
            index = 0;
        }

        public boolean hasNext() {
            return index < this.size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("RandomizedQueue is empty");
            }

            int currIndex = index;
            index += 1;
            return q[indexes[currIndex]];
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

        for (int i = 0; i < 5; i++) {
            q.enqueue(i);
            System.out.println("After enqueue " + i + " " + q.toString());
        }

        System.out.println("\nIterator 1");
        for (Iterator<Integer> iterator1 = q.iterator(); iterator1.hasNext();) {
            System.out.println("next -> " + iterator1.next());
        }

        System.out.println("\nIterator 2");
        for (Iterator<Integer> iterator2 = q.iterator(); iterator2.hasNext();) {
            System.out.println("next -> " + iterator2.next());
        }

        for (int j = 0; j < 5; j++) {
            System.out.println("Deque: " + q.dequeue() + " " + q.toString());
        }
        System.out.println("After dequeue: " + q.toString());
    }

}
