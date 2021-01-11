package Assignments;

import queue.ArrayBndQueue;
import array_list.ArrayUnsortedList;

import java.util.Scanner;

/*
 * Student: MHD Baraa Bashar Al-Masri
 * ID: 201910560
 * Date: Jan/05/2021
 */

public class SecondAssignment {
    public static void main(String[] argv) {
        Scanner reader = new Scanner(System.in);

        // question1 demo:
        System.out.println("Check if some strings are palindrome ");
        while (true) {
            System.out.println("press `f` to go to next demo");
            System.out.print("enter a string to check whether it's a palindrome or not: ");
            String s = reader.next();
            // quit condition
            if (s.equals("f")) {
                break;
            }
            System.out.println("result:");
            // queue has half the input string size since it stores only half of the string :)
            queuePalindrome(new ArrayBndQueue<>(s.length()/2), s);
            // new line ha ha
            System.out.println();
        }

        // queue2 demo:
        ArrayUnsortedList<Integer> list = new ArrayUnsortedList<>();

        System.out.println("enter list elements: \nenter `-16` to finish");

        while (true) {
            int num = reader.nextInt();

            if (num == -16) {
                break;
            }

            list.add(num);
        }

        System.out.println("enter bounds of range that you want to remove from the list:");
        System.out.print("lower bound = ");
        int l = reader.nextInt();
        System.out.print("upper bound = ");
        int u = reader.nextInt();

        filterListRange(list, l, u);
        System.out.println("list after range removal:");
        list.print();

    }

    ////////////////
    /* Question1: create a static method named
     * queuePalindrome that takes two parameters; a bounded queue of characters and a string s. The
     * method checks whether string s reads the same from both ways such as “Karak”, keeping other
     * elements in their original locations in the queue
     */
    public static void queuePalindrome(ArrayBndQueue<Character> q, String s) {
        /*
         * check if string is palindrome using a queue:
         * 1. add half of the string to the given queue(that has size of half the given string)
         * 2. compare the queue elements with the reverse second half of the string
         */

        // string length to call length() just once, since it's kinda an expensive call
        final int stringLength = s.length();

        // store first half of the string in the queue
        for (int i = 0; i < stringLength / 2; i++) {
            q.enqueue(s.charAt(i));
        }

        // compare the stored first half with the reversed second half of the string
        boolean palindrome = true;
        for (int i = stringLength - 1; i > (stringLength / 2); i--) {
            char c = q.dequeue();
            // unify char case to ease the comparison
            if (Character.toUpperCase(c)
                    != Character.toUpperCase(s.charAt(i))) {
                palindrome = false;
                break;
            }
        }

        // print the result
        if (palindrome) {
            System.out.println("String is palindrome");
        } else {
            System.out.println("String is not palindrome");
        }

    }

    ////////////////
    /* Question2: create a static method called filterListRange with three parameters as below.
     * Your method should remove the values between the range (the min &| max range bounds that are inclusive)
     *  specified by the user starting from the first item in the list
     */
    public static void filterListRange(ArrayUnsortedList<Integer> ul, int min, int max) {
        /*
         * remove a range of elements from a list:
         * 0. check if the range exists in the list
         * 1. set a removing flag ie will be activated when the lower range bound is reached
         * 2. remove elements when the flag is activated
         * 3. return when the upper range bound is reached, since it's over Anakin
         */
        if (!ul.contains(min) || !ul.contains(max)) {
            System.out.println("given range is outside the given list!");
            return;
        }

        ul.reset();
        // again getting rid of multiple method calls by the loop :)
        final int listSize = ul.size();

        // will be true when the min range bound is met
        boolean removeFlag = false;
        // current list element(iterator)
        int element = ul.getNext();

        for (int i = 0; i < listSize; i++) {
            // when the range's lower bound is met start the purging
            if (element == min) {
                removeFlag = true;
            }

            // remove in the given range
            if (removeFlag) {
                ul.remove(element);
            }

            // when the range's upper bound is met quit the method
            if (element == max) {
                return;
            }

            // update iterator
            element = ul.getNext();
        }
    }

}
