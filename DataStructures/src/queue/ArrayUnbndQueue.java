package queue;

public class ArrayUnbndQueue<T> implements UnboundedQueueInterface<T> {
    protected final int DEFCAP = 100;
    protected T[] queue;
    protected int origCap;
    protected int numElements = 0;
    protected int front = 0;
    protected int rear = -1;

    public ArrayUnbndQueue() {
        queue = (T[]) new Object[DEFCAP];
        rear = DEFCAP - 1;
        origCap = DEFCAP;
    }

    public ArrayUnbndQueue(int origCap) {
        queue = (T[]) new Object[origCap];
        rear = origCap - 1;
        this.origCap = origCap;
    }

    public void enqueue(T element) {
        if (numElements == queue.length)
            enlarge();
        rear = (rear + 1) % queue.length;
        queue[rear] = element;
        numElements = numElements + 1;
    }

    public T dequeue() {
        if (isEmpty())
            throw new QueueUnderflowException("Dequeue on empty queue.");
        else {
            T toReturn = queue[front];
            queue[front] = null;
            front = (front + 1) % queue.length;
            numElements = numElements - 1;
            return toReturn;
        }
    }

    public boolean isEmpty() {
        return (numElements == 0);
    }

    private void enlarge() {
        // create the larger array
        T[] larger = (T[]) new Object[queue.length + origCap];

        // copy the contents from the smaller array into the larger array
        int currSmaller = front;
        for (int currLarger = 0; currLarger < numElements; currLarger++) {
            larger[currLarger] = queue[currSmaller];
            currSmaller = (currSmaller + 1) % queue.length;
        }

        // update instance variables
        queue = larger;
        front = 0;
        rear = numElements - 1;
    }


}