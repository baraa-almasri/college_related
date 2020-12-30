import array_list.*;

import java.util.Scanner;

public class ArrayListDemo {
    private static final Scanner read = new Scanner(System.in);
    public static void main(String []argv) {
        ArrayUnsortedList<Integer> ul = new ArrayUnsortedList<>(5);
        ul.add(1);
        ul.add(1);
        ul.add(2);
        ul.add(3);
        ul.add(4);

//        fillList(ul, 5);

        System.out.printf("10 freq = %d\n", getOccList(ul, 10));
        ul.print();
        System.out.println();
        removeOddList(ul, 5);
        ul.print();
        System.out.println(ul.toString());
    }

    public static <T> void printList(ArrayUnsortedList<T> list) {
        list.reset();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.getNext());
        }
    }

    public static <T> void fillList(ArrayUnsortedList<T> list, int size) {
        System.out.println("enter list elements");
        for (int i = 0; i < size; i++) {
            list.add( (T)read.next() );
        }
    }

    public static <T> int getOccList(ArrayUnsortedList<T> list, T element) {
        list.reset();
        int counter = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.getNext().equals(element)) {
                counter++;
            }
        }

        return counter;
    }

    public static void removeOddList(ArrayUnsortedList<Integer> list, int s) {
        list.reset();
        int counter = 0;
        // size issues blyat
        // i.e. if i was to go from 0 until list.size() it'll miss some elements
        // since the size is shrinking and the index will go after it and so on....
        for (int i = 0; i < s; i++) {
            int x = list.getNext();
            if (x % 2 != 0) {
                list.remove(x);
            }
        }
    }
}
