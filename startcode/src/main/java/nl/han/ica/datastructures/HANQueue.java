package nl.han.ica.datastructures;

public class HANQueue<T> implements IHANQueue<T> {
    public HANLinkedList<T> queue;

    public HANQueue() {
        queue = new HANLinkedList<>();
    }

    /**
     * Clears list. Size equals 0 afterwards
     */
    @Override
    public void clear() {
        queue.clear();
    }

    /**
     * Checks whether queue is empty or not
     *
     * @return true when empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return queue.getSize() == 0;
    }

    /**
     * Adds value T to the back of the queue
     *
     * @param value value to add
     */
    @Override
    public void enqueue(T value) {
        queue.insert(queue.getSize(), value);
    }

    /**
     * Dequeues value at the front of the queue
     *
     * @return value T at the front of the Queue
     */
    @Override
    public T dequeue() {
        T res = queue.getFirst();
        queue.removeFirst();
        return res;
    }

    /**
     * Returns value at the front of the queue without removing
     *
     * @return value at the front without removing
     */
    @Override
    public T peek() {
        return queue.getFirst();
    }

    /**
     * Size of queue
     *
     * @return the number of items in queue
     */
    @Override
    public int getSize() {
        return queue.getSize();
    }
}
