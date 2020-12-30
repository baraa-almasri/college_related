package array_list;

public class ArrayUnsortedList<T> implements ListInterface<T> {
    protected final int DEFCAP = 100; // default capacity
    protected int origCap;            // original capacity
    protected T[] list;               // array to hold this list�s elements
    protected int numElements = 0;    // number of elements in this list
    protected int currentPos;         // current position for iteration

    // set by find method
    protected boolean found;  // true if element found, otherwise false
    protected int location;   // indicates location of element if found

    public ArrayUnsortedList() {
        list = (T[]) new Object[DEFCAP];
        origCap = DEFCAP;
    }

    public ArrayUnsortedList(int origCap) {
        list = (T[]) new Object[origCap];
        this.origCap = origCap;
    }

    protected void enlarge()
    // Increments the capacity of the list by an amount
    // equal to the original capacity.
    {
        // Create the larger array.
        T[] larger = (T[]) new Object[list.length + origCap];

        // Copy the contents from the smaller array into the larger array.
        for (int i = 0; i < numElements; i++) {
            larger[i] = list[i];
        }

        // Reassign list reference.
        list = larger;
    }

    protected void find(T target)
    // Searches list for an occurence of an element e such that
    // e.equals(target). If successful, sets instance variables
    // found to true and location to the array index of e. If
    // not successful, sets found to false.
    {
        location = 0;
        found = false;

        while (location < numElements) {
            if (list[location].equals(target)) {
                found = true;
                return;
            } else
                location++;
        }
    }

    @Override
    public void add(T element)
    // Adds element to this list.
    {
        if (numElements == list.length)
            enlarge();
        list[numElements] = element;
        numElements++;
    }

    @Override
    public boolean remove(T element)
    // Removes an element e from this list such that e.equals(element)
    // and returns true; if no such element exists, returns false.
    {
        find(element);
        if (found) {
            list[location] = list[numElements - 1];
            list[numElements - 1] = null;
            numElements--;
        }
        return found;
    }

    @Override
    public int size()
    // Returns the number of elements on this list.
    {
        return numElements;
    }

    @Override
    public boolean contains(T element)
    // Returns true if this list contains an element e such that
    // e.equals(element); otherwise, returns false.
    {
        find(element);
        return found;
    }

    @Override
    public T get(T element)
    // Returns an element e from this list such that e.equals(element);
    // if no such element exists, returns null.
    {
        find(element);
        if (found)
            return list[location];
        else
            return null;
    }

    @Override
    public String toString()
    // Returns a nicely formatted string that represents this list.
    {
        String listString = "List: ";
        for (int i = 0; i < numElements; i++)
            listString = String.format("%s%s, ", listString, this.list[i]);
        return listString.substring(0, listString.length()-2);
    }

    @Override
    public void reset()
    // Initializes current position for an iteration through this list,
    // to the first element on this list.
    {
        currentPos = 0;
    }

    @Override
    public T getNext()
    // Preconditions: The list is not empty
    //                The list has been reset
    //                The list has not been modified since the most recent reset
    //
    // Returns the element at the current position on this list.
    // If the current position is the last element, it advances the value
    // of the current position to the first element; otherwise, it advances
    // the value of the current position to the next element.
    {
        T next = list[currentPos];
        if (currentPos == (numElements - 1))
            currentPos = 0;
        else
            currentPos++;
        return next;
    }

    public void print() {
        System.out.println("List elements:");
        for (int i = 0; i < this.numElements; i++) {
            System.out.print(this.list[i] + " ");
        }
        System.out.println();
    }

    public T getFirstElement() {
        return this.list[0];
    }

    public T getLastElement() {
        return this.list[this.numElements-1];
    }

    public void removeFirstElement() {
        this.reset();
        this.remove(this.list[0]);
    }

    public void removeAllMatches(T element) {
        while (this.remove(element));
    }

}
