import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/*
 * @class BusySynchronizedQueue
 *
 * @brief Defines an implementation of the BlockingQueue interface
 *        that works properly when accessed via multiple threads since
 *        it's synchronized properly, but is inefficient since it
 *        "busy waits".
 */
class BusySynchronizedQueue<E> implements BlockingQueue<E> {
    /**
     * The queue consists of a List of E's.
     */
    private List<E> mList = new ArrayList<E>();

    /**
     * The maximum capacity of the queue or Integer.MAX_VALUE if none.
     */
    private final int mCapacity;

    /**
     * Create a SimpleBlocking queue with a capacity of
     * Integer.MAX_VALUE.
     */
    public BusySynchronizedQueue() {
        this(Integer.MAX_VALUE);
    }

    /**
     * Create a SimpleBlocking queue with the given capacity.
     */
    public BusySynchronizedQueue(int capacity) {
        if (capacity <= 0) 
            throw new IllegalArgumentException();
        mCapacity = capacity;
        mList = new ArrayList<E>();
    }

    /**
     * True if the queue is empty.
     */
    public synchronized boolean isEmpty() {
        return mList.size() == 0;
    }

    /**
     * Returns true if the queue is full, else false.
     */
    private synchronized boolean isFull() {
        return mList.size() == mCapacity;
    }

    /**
     * Add a new E to the end of the queue.
     */
    public synchronized void put(E msg) throws InterruptedException {
        if (isFull() == false)
            mList.add(msg);
    } 

    /**
     * Remove the E at the front of the queue.
     */
    public synchronized E take() throws InterruptedException {
        if (isEmpty() == false)
            return mList.remove(0);
        else
            return null;
    } 

    /**
     * Returns the number of elements in this queue.
     */
    public synchronized int size() {
        return mList.size();
    }

    /**
     * All these methods are inherited from the BlockingQueue
     * interface.  All are defined as no-ops for simplicity.
     */
    public int drainTo(Collection<? super E> c) {
        return 0;
    }
    public int drainTo(Collection<? super E> c, int maxElements) {
        return 0;
    }
    public boolean contains(Object o) {
        return false;
    }
    public boolean remove(Object o) {
        return false;
    }
    public int remainingCapacity() {
        return 0;
    }
    public E poll() {
        return null;
    }
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        return take();
    }
    public E peek() {
        return null;
    }
    public boolean offer(E e) {
        return false;
    }
    public boolean offer(E e, long timeout, TimeUnit unit) {
        try {
            put(e);
        }
        catch (InterruptedException ex) {
            // Just swallow this exception for this simple (buggy) test.
        }
        return true;
    }
    public boolean add(E e) {
        return false;
    }
    public E element() {
        return null;
    }
    public E remove() {
        return null;
    }
    public void clear() {
    }
    public boolean retainAll(Collection<?> collection) {
        return false;
    }
    public boolean removeAll(Collection<?> collection) {
        return false;
    }
    public boolean addAll(Collection<? extends E> collection) {
        return false;
    }
    public boolean containsAll(Collection<?> collection) {
        return false;
    }
    public Object[] toArray() {
        return null;
    }
    public <T> T[] toArray(T[] array) {
        return null;
    }
    public Iterator<E> iterator() {
        return null;
    }
}






