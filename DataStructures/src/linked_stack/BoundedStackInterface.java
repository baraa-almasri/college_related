package linked_stack;

import stack.StackOverflowException;

public interface BoundedStackInterface<T> extends StackInterface<T> {
    void push(T element) throws StackOverflowException;

    boolean isFull();
}
