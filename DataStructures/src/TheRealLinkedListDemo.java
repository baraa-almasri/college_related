import linked_list.*;

public class TheRealLinkedListDemo {
    public static void main(String []argv) {

        RefSortedList<Integer> rf = new RefSortedList<>();
        rf.add(1);
        rf.add(100);
        printLL(rf);

    }

    static <T extends Comparable<T>> void printLL(RefSortedList<T> list) {
        list.reset();
        for (int i = 0; i < list.size(); i++) {
            T x = list.getNext();
            System.out.println(x);
        }
    }

}
