package stack;

public class ArrayStack<T> implements BoundedStackInterface<T> {
    protected final int DEFCAP = 100;
    protected T[] stack;
    protected int topIndex = -1;

    public ArrayStack() {
        stack = (T[]) new Object[DEFCAP];
    }

    public ArrayStack(int maxSize) {
        stack = (T[]) new Object[maxSize];
    }

    public boolean isEmpty() {
        return this.topIndex == -1;
    }

    public boolean isFull() {
        return topIndex == stack.length - 1;
    }

    public void push(T element) {
        if (!isFull()) {
            topIndex++;
            stack[topIndex] = element;
        } else
            throw new StackOverflowException("push attempted on a full stack ");
    }

    public void pop() {
        if (!isEmpty()) {
            this.
                stack[topIndex] = null;
            topIndex--;
        } else
            throw new StackUnderflowException("Pop attempted on an empty stack ");
    }

    public T top() {
        T topOfStack = null;
        if (!isEmpty())
            topOfStack = stack[topIndex];
        else
            throw new StackOverflowException("Top attempted on a full stack ");

        return topOfStack;
    }


}
