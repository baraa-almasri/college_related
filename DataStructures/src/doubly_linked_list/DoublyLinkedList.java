
package doubly_linked_list;

public class DoublyLinkedList<T> {
    private DLLNode<T> first;
    private DLLNode<T> last;
    private int numElements;

    public DoublyLinkedList() {
        first = null;
        last = null;
        numElements = 0;
    }

    // ------- invoked when an object is printed out
    @Override
    public String toString() {
        String listElements = "Doubly linked List contains:\n ";
        DLLNode<T> t = first;
        while (t != null) {
            listElements += t.getInfo() + "\t";
            t = t.getFront();
        }
        return listElements;
    }

    // --------------------------------
    public void addFirst(T element) {
        DLLNode<T> n = new DLLNode<>(element);
        if (first == null) {
            first = last = n;
        } else {
            n.setFront(first);
            first.setBack(n);
            first = n;
        }
        ++numElements;
    }

    public void addLast(T element) {
        DLLNode<T> n = new DLLNode<>(element);

        if (last == null) {
            first = last = n;
        } else {
            last.setFront(n);
            n.setBack(last);
            last = n;
        }
        ++numElements;
    }

    public void removeFirst() {
        if (first == null || first.getFront() == null) {
            first = last = null;
            numElements = 0;
        } else {
            first = first.getFront();
            first.setBack(null);
            --numElements;
        }
    }

    public void removeLast() {
        if (last == null || last.getBack() == null) {
            first = last = null;
            numElements = 0;
        } else {
            last = last.getBack();
            last.setFront(null);
            --numElements;
        }
    }

    public void remove(T element) {
        if (first == null) // empty list
            return;
        else {
            if (first.getFront() == null) // contains one node
            {
                if (first.getInfo().equals(element)) {
                    first = last = null;
                    numElements = 0;
                }
                return;
            } else // more than one node
            {
                DLLNode<T> t = first;
                while (t != null && !t.getInfo().equals(element))
                    t = t.getFront();
                if (t == null)
                    return;
                if (t == last)
                    removeLast();
                else if (t == first)
                    removeFirst();
                else {
                    t.getBack().setFront(t.getFront());
                    t.getFront().setBack(t.getBack());
                    --numElements;
                }

            }
        }
    }

}
