package linked_stack;

public class StackOverflowException extends RuntimeException {
    public StackOverflowException() {
        super();
    }

    public StackOverflowException(String msg) {
        super(msg);
    }
}
