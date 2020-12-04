package stack;

import java.util.ArrayList;

public class ListStack<T> implements UnboundedStack<T> {
    private final ArrayList<T> stack;
    private int topIndex;

    public ListStack() {
        this.topIndex = -1;
        this.stack = new ArrayList<>();
    }

    public void push(T element) {
        this.topIndex++;
        this.stack.add(element);
    }

    public void pop() {
        if (this.topIndex > -1) {
            this.stack.remove(this.topIndex--);

        } else {
            throw new StackUnderflowException("empty stack");
        }
    }

    public T top() {
        if (this.topIndex == -1) {
            throw new StackUnderflowException("empty stack");
        }

        return this.stack.get(this.topIndex);
    }

    public boolean isEmpty() {
        return this.topIndex == -1;
    }


}
