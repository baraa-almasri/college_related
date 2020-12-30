import linked_list.LLNode;

public class LinkedListDemo {
    public static void main(String []argv) {
        LLNode<Integer> l1 = new LLNode<>(2);
        l1.setLink(new LLNode<>(5));

        LLNode<Integer> l2 = new LLNode<>(10);
        l2.setLink(l1.getLink());
        l1.getLink().setLink(new LLNode<>(8));

        l1.setLink(l2);
        LLNode<Integer> tmp = l1;
        while (tmp.getLink() != null) {
            tmp = tmp.getLink();
            tmp.setLink(null);
        }
        /*
        printList(l1);
        System.out.printf("sum = %d\n", sumLL(l1));
        System.out.printf("avg = %d\n", avgLL(l1));
        System.out.printf("8 exists: %b\n", findLLElement(l1, 8));
*/
    }

    static <T> boolean findLLElement(LLNode<T> node, T element) {
        LLNode<T> tmp = node;
        while (tmp != null) {
            if (tmp.getInfo().equals(element)) {
                return true;
            }
            tmp = tmp.getLink();
        }

        return false;
    }

    static int avgLL(LLNode<Integer> node) {
        LLNode<Integer> tmp = node;
        int sum = 0;
        int elementCount = 0;

        while (tmp != null) {
            sum += tmp.getInfo();
            elementCount++;
            tmp = tmp.getLink();
        }

        return sum/elementCount;
    }

    static int sumLL(LLNode<Integer> node) {
        LLNode<Integer> tmp = node;
        int sum = 0;
        while (tmp != null) {
            sum += tmp.getInfo();
            tmp = tmp.getLink();
        }

        return sum;
    }

    static <T> void printList(LLNode<T> node) {
        LLNode<T> tmp = node;
        while (tmp != null) {
            System.out.print(tmp.getInfo() + " ");
            tmp = tmp.getLink();
        }
        System.out.println();
    }
}
