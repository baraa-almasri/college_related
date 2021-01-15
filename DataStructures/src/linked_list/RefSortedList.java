/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package linked_list;

/**
 * @author lab1257-15
 */
public class RefSortedList<T extends Comparable<T>>
    extends RefUnsortedList<T>
    implements ListInterface<T> {
    public RefSortedList() {
        super();
    }

    @Override
    public void add(T element)
// Adds element to this list.
    {
        LLNode<T> prevLoc; // trailing reference
        LLNode<T> location; // traveling reference
        T listElement; // current list element being compared
// Set up search for insertion point.
        location = list;
        prevLoc = null;
// Find insertion point.
        while (location != null) {
            listElement = location.getInfo();
            if (listElement.compareTo(element) < 0) // list element < add element
            {
                prevLoc = location;
                location = location.getLink();
            } else
                break;
        }
// Prepare node for insertion.
        LLNode<T> newNode = new LLNode<T>(element);
// Insert node into list.
        if (prevLoc == null) {
// Insert as first node.
            newNode.setLink(list);
            list = newNode;
        } else {
// Insert elsewhere.
            newNode.setLink(location);
            prevLoc.setLink(newNode);
        }
        numElements++;
    }

    public void delSpan(int i, int n) {
        if (i > this.size()-1 || i+n > this.size()-1) {
            System.out.println("index out of bound");
            return;
        }

        this.reset();
        LLNode<T> tmp = this.list;

        for (int c = 0; c < i; c++) {
            tmp = tmp.getLink();
        }

        for (int c = i; c <= n+1; c++) {
            this.remove(tmp.getInfo());
            tmp = tmp.getLink();
        }


    }

    public void evenUp() {
        LLNode<Integer> tmp = (LLNode<Integer>) this.list;

        while (tmp != null) {
            if (tmp.getInfo() % 2 != 0) {
                tmp.setInfo(tmp.getInfo()+1);
            }
            tmp = tmp.getLink();
        }

    }
}