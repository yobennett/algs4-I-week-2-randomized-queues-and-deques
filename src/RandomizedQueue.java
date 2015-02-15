import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int capacity;
    private int size;

//    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        capacity = 1;
        q = (Item[]) new Object[capacity]; // ugly cast
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

//    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Item[] copy = (Item[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            copy[i] = q[i];
        }
        q = copy;
        capacity = newCapacity;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot add null item");
        }

        // resize if not enough capacity
        if (size() + 1 > capacity) {
            resize(2 * capacity);
        }

        q[size] = item;
        size += 1;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("RandomizedQueue is empty");
        }

        // random sample, swap sample item and last item, reduce size
        RandomSample sample = new RandomSample();

        size -= 1;
        q[sample.index()] = q[size];
        q[size] = null;

        // resize down to 1/4 capacity (or 1 if empty)
        if (!isEmpty() && (size() <= capacity / 4)) {
            resize(capacity / 2);
        } else if (isEmpty()) {
            resize(1);
        }

        return sample.value();
    }

    private class RandomSample {

        private int index;
        private Item value;

        public RandomSample() {
            this.index = StdRandom.uniform(size);
            this.value = q[index];
        }

        public int index() {
            return this.index;
        }

        public Item value() {
            return this.value;
        }

    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("RandomizedQueue is empty");
        }

        RandomSample sample = new RandomSample();
        return sample.value();
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator(size);
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

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

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("RandomizedQueue is empty");
            }

            int currIndex = index;
            index += 1;
            return q[indexes[currIndex]];
        }

    }

    // unit testing
    public static void main(String[] args) {
    }

}
