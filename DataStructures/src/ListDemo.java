import java.util.Scanner;
import array_list.*;

public class ListDemo {

    static Scanner in = new Scanner(System.in);

    public static void fillList(ArrayUnsortedList<Integer> t, int s) {

        System.out.println("Fill in the list");
        for (int i = 0; i < t.size(); i++) {
            t.add(in.nextInt());

        }
    }


    public static void printList(ArrayUnsortedList<Integer> t, int size) {
        System.out.println("List elements are:");
        t.reset();

        for (int i = 0; i < size; i++) {
            System.out.println(t.getNext() + " "); // we can say that x is getnext : int x= us.getnext; .... s.o.p(x);

        }

    }

    public static int getOccList(ArrayUnsortedList<Integer> us, int val) {
        int counter = 0;
        us.reset();

        for (int i = 0; i < us.size(); i++) {
            int x = us.getNext();
            if (x == val)
                counter++;

        }

        return counter;
    }


    public static void removeOddList(ArrayUnsortedList<Integer> us, int s) {

        us.reset();

        for (int i = 0; i < s; i++) {
            int x = us.getNext();
            if (x % 2 != 0) {

                us.remove(x);
            }

        }
    }


    // ------------------------- Q2 ASSIGNMENT -----------------------------

    public static void filterListRange(ArrayUnsortedList<Integer> f, int min, int max) {
        ArrayUnsortedList<Integer> t = new ArrayUnsortedList<>();
        f.reset();
        for (int i = 0; i < f.size(); i++) {
            int j = f.getNext();
            if (j >= min && j <= max)
                continue;
            t.add(j);
        }
        f.clearList();
        for (int i = 0; i < t.size(); i++) {
            f.add(t.getNext());
        }
    }


    public static void main(String[] args) {

//    ArrayUnsortedList<Integer> us=new ArrayUnsortedList<>(5);
//        us.add(12);
//        us.add(10);
//        us.add(5);
//        us.add(0);
//        us.remove(12);
//        us.add(100);
//        fillList(us,5);
//  printList(us,5);
//        System.out.println("Enter val");
//       int val=in.nextInt();
//        System.out.println(getOccList(us,val));
//        removeOddList(us,5);

        //--------------- Q2 ASSIGNMENT--------------------

        ArrayUnsortedList<Integer> t = new ArrayUnsortedList<>();
        System.out.println("enter a min value");
        int min = in.nextInt();
        System.out.println("enter a max value");
        int max = in.nextInt();
        t.add(2);
        t.add(19);
        t.add(1);
        t.add(5);
        t.add(8);
        //   t.add(9);
        // t.add(4);
        // fillList(t,7);
        filterListRange(t, min, max);
        printList(t, t.size());


    }


}
