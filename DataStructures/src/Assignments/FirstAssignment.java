package Assignments;

import stack.*;
import java.util.Scanner;

/*
 * Student: MHD Baraa Bashar Al-Masri
 * ID: 201910560
 * Date: Nov/25/2020
 */

public class FirstAssignment {
    public static final Scanner read = new Scanner(System.in);

    public static void main(String[] argv) {
        System.out.println("enter sizes of stacks: ");
        System.out.print("size1: ");
        int size1 = read.nextInt();
        System.out.print("size2: ");
        int size2 = read.nextInt();

        ArrayStack<Integer> stk1 = new ArrayStack<>(size1);
        ArrayStack<Integer> stk2 = new ArrayStack<>(size2);
        System.out.println("enter stack1 elements:");
        readStack(stk1);
        System.out.println("enter stack2 elements:");
        readStack(stk2);

        // question1 demo
        System.out.printf(
                "distinct elements between the two stacks: %d\n\n",
                countStackDistinct(stk1, stk2, size1, size2)
        );

        // question2 demo
        System.out.println("stack2 before removing top 5 elements");
        printStack(stk2, size2);
        removeTopNthStackElement(stk2, 5);
        System.out.println("\nstack2 after removing top 5 elements");
        printStack(stk2, size2);

    }
    ////////////////
    /*
     * Question1: create a generic static method called countStackDistinct( ) that receives two generic
     * stacks s1 and s2. The method returns an integer value that represents a counter for
     * the distinct number of elements in the two stacks s1 and s2
     */
    public static <T> int
    countStackDistinct(
            ArrayStack<T> stk1, ArrayStack<T> stk2, int size1, int size2
    ) { 
        /* 
         * this weird looking function works as the following:
         *
         * 1. copy stacks into temporary stacks that won't be modified during the function
         * 2. store overlapped elements in a 3rd-party stack
         * 3. compare the 3rd-party stack with the two stacks, and eliminate overlapped elements from them
         * 4. count what remained from stack1 and stack2 (since we eliminated overlaps)
         * 5. restore stacks from the peaceful stacks that DIDN'T get modified :)
         *
         */
        
        // copy stacks into temporary stacks that won't be modified
        // to restore them at the end of the function
        ArrayStack<T> originalStk1Copy = new ArrayStack<>(size1);
        ArrayStack<T> originalStk2Copy = new ArrayStack<>(size2);
        copyStack(stk1, originalStk1Copy, size1);
        copyStack(stk2, originalStk2Copy, size2);
        
        
        // temporary stacks(classic)
        ArrayStack<T> tmp1 = new ArrayStack<>(size1);
        ArrayStack<T> tmp2 = new ArrayStack<>(size2);
        
        // overlapped elements between the two stacks 
        // to compare them with it later
        ArrayStack<T> overlappingElements
                = new ArrayStack<>(size1+size2); // if the two stacks shares every element
        
        // collecting overlaped elements
        while (!stk1.isEmpty()) {
            tmp1.push(stk1.top());
            
            while (!stk2.isEmpty()) {
                if(stk1.top().equals(stk2.top())) {
                    overlappingElements.push(stk1.top());
                }
                tmp2.push(stk2.top());
                stk2.pop();
            }
            
            while (!tmp2.isEmpty()) {
                stk2.push(tmp2.top());
                tmp2.pop();
            }
            
            if (!stk1.isEmpty()) {
                stk1.pop();
            }
            
        }
        
        ////////////////
        ArrayStack<T> everyonesTmp 
                = new ArrayStack<>(size1+size2); // it'll be used with stack1 or stack2 so better make big :)
        
        ArrayStack<T> overlapedTmp = new ArrayStack<>(size1+size2);
        
        // comparing overlaped elements with stack1(tmp1 at this point)
        // and pop overlaped elements from stack1
        while (!overlappingElements.isEmpty()) {
            overlapedTmp.push(overlappingElements.top());
            
            while (!tmp1.isEmpty()) {
                if(overlappingElements.top().equals(tmp1.top())) {
                    tmp1.pop();
                    continue;
                }
                everyonesTmp.push(tmp1.top());
                tmp1.pop();
            }
            
            while (!everyonesTmp.isEmpty()) {
                tmp1.push(everyonesTmp.top());
                everyonesTmp.pop();
            }
            
            if (!overlappingElements.isEmpty()) {
                overlappingElements.pop();
            }
            
        }
        ////////////////
        
        // restore overlapped elements 
        while (!overlapedTmp.isEmpty()) {
            overlappingElements.push(overlapedTmp.top());
            overlapedTmp.pop();
        }
        
        // same but stack2
        while (!overlappingElements.isEmpty()) {
            //tmp1.push(stk1.top());
            
            while (!stk2.isEmpty()) {
                if(overlappingElements.top().equals(stk2.top())) {
                    stk2.pop();
                    //overlappingElements.push(stk1.top());
                    //stk2.pop();
                    continue;
                }
                everyonesTmp.push(stk2.top());
                //stk.pop();
                stk2.pop();
            }
            
            while (!everyonesTmp.isEmpty()) {
                stk2.push(everyonesTmp.top());
                everyonesTmp.pop();
            }
            
            if (!overlappingElements.isEmpty()) {
                overlappingElements.pop();
            }
            
        }
        ////////////////
        
        // finally count elements that remained in stack1(tmp1 at this poing) 
        // and stack2 since overlaps has been taken care of :) 
        int distinctElements = 0;
        
        while (!tmp1.isEmpty()) {
            distinctElements++;
            tmp1.pop();
        }

        while (!stk2.isEmpty()) {
            distinctElements++;
            stk2.pop();
        }
        ////////////////
        
        // restoring the stacks after the mess above :)
        copyStack(originalStk1Copy, stk1, size1);
        copyStack(originalStk2Copy, stk2, size2);
        
        return distinctElements;
    }
    ////////////////
    /*
     * Question2: create a generic static method named removeTopNthStackElement( ) that receives two
     * parameters a generic stack and an integer n. The method should
     * remove the top n elements from stack, if possible.
     */
    public static <T> void
    removeTopNthStackElement(ArrayStack<T> stk, int numberOfRemovedElements) {

        while (!stk.isEmpty() &&
                numberOfRemovedElements-- > 0) {

            stk.pop();
        }

    }

    // supporting functions :)
    private static <T> void
    copyStack(ArrayStack<T> source, ArrayStack<T> target, int size) {

        ArrayStack<T> tmp = new ArrayStack<>(size);
        while (!source.isEmpty()) {
            try {
                target.push(source.top());
            } catch (StackOverflowException sof) {
                System.out.println("size of target stack exceeded!");
                break;
            }
            
            tmp.push(source.top());
            source.pop();
        }

        while (!tmp.isEmpty()) {
            source.push(tmp.top());
            tmp.pop();
        }
    }

    public static <T> void printStack(ArrayStack<T> stk, int size) {
        ArrayStack<T> tmp = new ArrayStack<>(size);
        while (!stk.isEmpty()) {
            System.out.println(stk.top());
            tmp.push(stk.top());
            stk.pop();
        }

        while (!tmp.isEmpty()) {
            stk.push(tmp.top());
            tmp.pop();
        }
    }

    public static <T> void readStack(ArrayStack<T> stk) {
        while (!stk.isFull()) {
            stk.push((T)read.next());
        }
    }

}
