package linked_stack;

import stack.StackUnderflowException;

public interface StackInterface<T> {
    void pop() throws StackUnderflowException;

    T top() throws StackUnderflowException;

    boolean isEmpty();
}

