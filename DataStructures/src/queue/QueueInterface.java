package queue;

public interface QueueInterface<T> {
    T dequeue() throws QueueUnderflowException;
    boolean isEmpty();
}