package stack;

public class StackUnderflowException extends RuntimeException {
    public StackUnderflowException() {
        super();
    }

    public StackUnderflowException(String msg) {
        super(msg);
    }
}
