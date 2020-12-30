import linked_list.LLNode;

public class LLBonusQuestion {
    public static void main(String []argv) {
        // create list 40, 2, 1, 100
        LLNode<Integer> list = new LLNode<>(40);
        list.setLink(new LLNode<>(2)
            .setLink(new LLNode<>(1)
                .setLink(new LLNode<>(100))));

        // create new node w/ 10 after 40
        list.setLink(new LLNode<>(10)
            .setLink(list.getLink()));
        // new list 40, 10, 2, 1, 100

        // create new node w/ 10 after 2
        list.getLink().getLink()
            .setLink(new LLNode<>(10)
                .setLink(list.getLink().getLink().getLink()));
        // new list 40, 10, 2, 10, 1, 100

        // delete node 40
        list = list.getLink();
        // new list 10, 2, 10, 1, 100

        // delete node 2
        list.setLink(list.getLink().getLink());
        // new list 10, 10, 1, 100

        // delete node 40
        list.getLink().getLink().setLink(null);
        // new list 10, 10, 1

        printList(list);

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
