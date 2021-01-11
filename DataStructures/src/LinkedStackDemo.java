import linked_stack.*;

public class LinkedStackDemo {

    public static void main(String[] args) {
        // same as the array stack demo but linked blyat
        LinkedStack<Integer> stk = new LinkedStack<>();
        stk.push(69);
        stk.push(13);
        stk.push(55);
        stk.push(23);
        stk.push(655);
        stk.push(19);
        stk.push(18);
        stk.push(17);
        stk.push(420);
        stk.push(12);
        stk.push(88);
        stk.push(-89);

        System.out.println("a peaceful stack that we'll do some stuff on it :)");
        // press F to pray
        stk.print();

        System.out.printf("sum of stack elements: %d\n", getSumOfStack(stk));

        System.out.println("removing first stack element");
        stk.removeFirst();
        stk.print();

        System.out.printf("max element: %d\n\n", getMaxElementFromStack(stk));

        System.out.println("stack before removing lower half");
        stk.print();
        deleteLowerHalf(stk, 11);
        System.out.println("stack after removing lower half");
        stk.print();

        System.out.println("stack before removing max element");
        stk.print();
        removeMaxFromStack(stk);
        System.out.println("stack after removing max element");
        stk.print();

        System.out.println("stack before removing 88");
        stk.print();

        removeElementFromStack(stk, 88);
        System.out.println("stack after removing 88");
        stk.print();

        System.out.println("stack before removing odds");
        stk.print();
        removeOddsFromStack(stk);
        System.out.println("stack after removing odds");
        stk.print();

        LinkedStack<String> s = new LinkedStack<>();
        s.push("abc");
        s.push("abcvd");
        s.push("ab");
        s.push("abce");
        s.push("abcff");

        System.out.printf("max length string: %s\n", getMaxLengthStringFromStack(s));

    }

    public static String getMaxLengthStringFromStack(LinkedStack<String> stk) {
        String max = stk.top();

        LinkedStack<String> tmp = new LinkedStack<>();
        while (!stk.isEmpty()) {
            tmp.push(stk.top());
            if (stk.top().length() > max.length()) {
                max = stk.top();
            }
            stk.pop();
        }

        return max;
    }

    public static void removeMaxFromStack(LinkedStack<Integer> stk) {
        Integer max = getMaxElementFromStack(stk);
        removeElementFromStack(stk, max);
    }

    public static <T> void
    removeElementFromStack(LinkedStack stk, T element) {

        LinkedStack tmp = new LinkedStack();
        while (!stk.isEmpty()) {
            if (stk.top().equals(element)) {
                stk.pop();
                continue;
            }
            tmp.push(stk.top());
            stk.pop();
        }

        while (!tmp.isEmpty()) {
            stk.push(tmp.top());
            tmp.pop();
        }

    }

    public static void removeOddsFromStack(LinkedStack<Integer> stk) {
        LinkedStack<Integer> evens = new LinkedStack<>();

        while (!stk.isEmpty()) {
            if (stk.top() % 2 == 0) {
                evens.push(stk.top());
            }
            stk.pop();
        }

        while (!evens.isEmpty()) {
            stk.push(evens.top());
            evens.pop();
        }

    }

    public static int getSumOfStack(LinkedStack<Integer> stk) {
        int sum = 0;

        LinkedStack<Integer> tmp = new LinkedStack<>();

        while (!stk.isEmpty()) {
            sum += stk.top();
            tmp.push(stk.top());
            stk.pop();
        }

        while (!tmp.isEmpty()) {
            stk.push(tmp.top());
            tmp.pop();
        }

        return sum;
    }

    public static void deleteLowerHalf(LinkedStack<Integer> stk, int size) {

        LinkedStack<Integer> tmp = new LinkedStack<>();

        while (!stk.isEmpty()) {
            tmp.push(stk.top());
            stk.pop();
        }

        for (int i = 0; i < size / 2 && !tmp.isEmpty(); i++) {
            tmp.pop();
        }

        while (!tmp.isEmpty()) {
            stk.push(tmp.top());
            tmp.pop();
        }

    }

    public static Integer getMaxElementFromStack(LinkedStack<Integer> stk) {
        Integer max = stk.top();

        LinkedStack<Integer> tmp = new LinkedStack<>();
        while (!stk.isEmpty()) {
            if (stk.top() > max) {
                max = stk.top();
            }
            tmp.push(stk.top());
            stk.pop();
        }

        while (!tmp.isEmpty()) {
            stk.push(tmp.top());
            tmp.pop();
        }

        return max;
    }


    public static void fillStack(LinkedStack<Integer> stk) {
        System.out.println("Enter stack elements");
        /*while (!stk.isFull()) {
            stk.push(read.nextInt());
        }*/
    }

}
