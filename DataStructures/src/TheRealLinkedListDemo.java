import linked_list.*;

public class TheRealLinkedListDemo {
    public static void main(String []argv) {

        RefSortedList<Integer> rf = new RefSortedList<>();
        rf.add(1);
        rf.add(100);
        rf.add(100);
        rf.add(69);
        rf.add(420);
        clearLL(rf, rf.size());
        rf.add(33);
        printLL(rf);
        /*System.out.println();
        rf.removeLast();
        printLL(rf);
        System.out.println();
        System.out.printf("first element: %d\n", rf.getFirst());
        System.out.printf("last element: %d\n", rf.getLast());

        rf.addAfterLast(333);
        printLL(rf);

        removeOcc(rf, 100);
    */}

    static <T extends Comparable<T>> void printLL(RefSortedList<T> list) {
        list.reset();
        for (int i = 0; i < list.size(); i++) {
            T x = list.getNext();
            System.out.println(x);
        }
    }

    static <T extends Comparable<T>> void removeOcc(RefSortedList<T> list, T element) {
        list.reset();
        while (list.remove(element));
    }

    static <T extends Comparable<T>> void clearLL(RefSortedList<T> list, int size) {
        list.reset();
        T next = list.getNext();
        for (int i = 0; i < size; i++) {
            list.remove(next);
            next = list.getNext();
        }
    }

}
