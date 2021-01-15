/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package linked_list;

import javax.print.attribute.standard.NumberOfDocuments;

/**
 * @author lab1257-15
 */
public class RefUnsortedList<T> implements ListInterface<T> {
    protected int numElements; // number of elements in this list
    protected LLNode<T> currentPos; // current position for iteration
    // set by find method
    protected boolean found; // true if element found, else false
    protected LLNode<T> location; // node containing element, if found
    protected LLNode<T> previous; // node preceeding location
    protected LLNode<T> list; // first node on the list

    //--------------------------------
    public RefUnsortedList() {
        numElements = 0;
        list = null;
        currentPos = null;
    }

    @Override
    public void add(T element)
// Adds element to this list.
    {
        LLNode<T> newNode = new LLNode<T>(element);
        newNode.setLink(list);
        list = newNode;
        numElements++;
    }

    //--------------------------------

    protected void find(T target)
// Searches list for an occurence of an element e such that
// e.equals(target). If successful, sets instance variables
// found to true, location to node containing e, and previous
// to the node that links to location. If not successful, sets 
// found to false.
    {
        location = list;
        found = false;
        while (location != null) {
            if (location.getInfo().equals(target)) // if they match
            {
                found = true;
                return;
            } else {
                previous = location;
                location = location.getLink();
            }
        }
    }

    //------------------------------
    @Override
    public int size()
// Returns the number of elements on this list. 
    {
        return numElements;
    }

    //------------------------------
    @Override
    public boolean contains(T element)
// Returns true if this list contains an element e such that 
// e.equals(element); otherwise, returns false.
    {
        find(element);
        return found;
    }

    //--------------------------------
    @Override
    public boolean remove(T element)
// Removes an element e from this list such that e.equals(element)
// and returns true; if no such element exists, returns false.
    {
        find(element);
        if (found) {
            if (list == location)
                list = list.getLink(); // remove first node
            else
                previous.setLink(location.getLink()); // remove node at location
            numElements--;
        }
        return found;
    }

    //---------------------------------
    @Override
    public T get(T element)
// Returns an element e from this list such that e.equals(element);
// if no such element exists, returns null.
    {
        find(element);
        if (found)
            return location.getInfo();
        else
            return null;
    }

    //---------------------------------
    @Override
    public String toString()
// Returns a nicely formatted string that represents this list.
    {
        LLNode<T> currNode = list;
        String listString = "List:\n";
        while (currNode != null) {
            listString = listString + " " + currNode.getInfo() + "\t";
            currNode = currNode.getLink();
        }
        return listString;
    }

    //--------------------------------
    @Override
    public void reset()
// Initializes current position for an iteration through this list,
// to the first element on this list.
    {
        currentPos = list;
    }

    //----------------------------------
    @Override
    public T getNext()
// Preconditions: The list is not empty
// The list has been reset
// The list has not been modified since most recent reset
//
// Returns the element at the current position on this list.
// If the current position is the last element, then it advances the value 
// of the current position to the first element; otherwise, it advances
// the value of the current position to the next element.
    {
        T next = currentPos.getInfo();
        if (currentPos.getLink() == null)
            currentPos = list;
        else
            currentPos = currentPos.getLink();
        return next;
    }



    public void removeFirst() {
        if (this.list != null) {
            this.list = this.list.getLink();
            this.numElements--;
        }
    }

    public void removeLast() {
        LLNode<T> tmp = list;
        while (tmp != null) {
            if (tmp.getLink().getLink() == null) {
                tmp.setLink(null);
                this.numElements--;
                return;
            }
            tmp = tmp.getLink();
        }
        /*
        while (tmp.getLink() != null) {
            tmp = tmp.getLink();
        }
        tmp.setLink(null);
        */
    }

    public T getFirst() {
        return this.list != null? this.list.getInfo(): null;
    }

    public T getLast() {
        LLNode<T> tmp = list;
        while (tmp != null) {
            if (tmp.getLink() == null) {
                return tmp.getInfo();
            }
            tmp = tmp.getLink();
        }

        return null;
    }

    public void addAfterLast(T element) {
        LLNode<T> tmp = list;
        while (tmp != null) {
            if (tmp.getLink() == null) {
                tmp.setLink(new LLNode<>(element));
                this.numElements++;
                return;
            }
            tmp = tmp.getLink();
        }
    }

    public void removeFirstNItems(int n) {
        if (this.numElements < n) {
            System.out.println("can't remove elements");
            return;
        }

        this.reset();
        T tmp = this.getNext();

        for (int i = 0; i < n; i++) {
            this.remove(tmp);
            tmp = this.getNext();
        }

    }


    public void printFirstNth(int n) {
        if (this.numElements < n) {
            System.out.println("can't remove elements");
            return;
        }

        this.reset();
        T tmp = this.getNext();
        for (int i = 1; i <= n; i++) {
            System.out.println(tmp);
            tmp = this.getNext();
        }

    }

    public void delete2ndHalf() {
        if (this.list == null) {
            System.out.println("empty list");
            return;
        }

        LLNode<T> tmp = this.list;
        int halfElements = this.size()/2;

        for (int i = 0; i < halfElements-1; i++) {
            tmp = tmp.getLink();
        }

        tmp.setLink(null);

    }

    public void delFirstEven() {
        this.reset();
        T tmp = this.list.getInfo();

        for (int i = 0; i < this.size(); i++) {
            if ((int)tmp % 2 == 0) {
                this.remove(tmp);
                return;
            }
            tmp = this.getNext();
        }
    }

    public void delNodeAt(int i) {
        if (i > this.size()-1) {
            System.out.println("index out of bound");
            return;
        }

        this.reset();
        LLNode<T> tmp = this.list;

        for (int c = 0; c < i-1; c++) {
            tmp = tmp.getLink();
        }

        this.remove(tmp.getInfo());
    }

    public void addAt(int i, T e) {
        if (i > this.size()-1) {
            System.out.println("index out of bound");
            return;
        }

        this.reset();
        LLNode<T> tmp = this.list;

        for (int c = 0; c < i-1; c++) {
            tmp = tmp.getLink();
        }

        LLNode<T> newNode = new LLNode<>(e);
        newNode.setLink(tmp.getLink());

        tmp.setLink(newNode);

        newNode = null;

    }

    public int getSum() {
        return getSumRecursive((LLNode<Integer>) this.list);
    }

    private int getSumRecursive(LLNode<Integer> node) {
        if (node == null) {
            return 0;
        } else {
            return node.getInfo() + getSumRecursive(node.getLink());
        }

    }

    public void print() {
        printRec(this.list);
    }

    public void printRec(LLNode<T> node) {
        if (node == null) {
            return;
        } else {
            System.out.println(node.getInfo());
            printRec(node.getLink());
        }
    }

}
