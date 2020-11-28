/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stack;


/**
 *
 * @author user
 */
public interface BoundedStackInterface <T> extends StackInterface<T>
{
    void push(T element) throws StackOverflowException;
    boolean isFull();             
}
