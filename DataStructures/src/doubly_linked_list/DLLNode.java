
package doubly_linked_list;


public class DLLNode<T> {
    private T info;
    private DLLNode<T> front;
    private DLLNode<T> back;

    public DLLNode(T info) {
        this.info = info;
        this.front = null;
        this.back = null;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public void setFront(DLLNode<T> front) {
        this.front = front;
    }

    public void setBack(DLLNode<T> back) {
        this.back = back;
    }

    public T getInfo() {
        return info;
    }

    public DLLNode<T> getFront() {
        return front;
    }

    public DLLNode<T> getBack() {
        return back;
    }
    
    
    
}
