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
}