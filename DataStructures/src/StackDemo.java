import stack.ArrayStack;
import java.util.Scanner;

public class StackDemo {

    static Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayStack<Integer> stk = new ArrayStack<>(12);
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
        printStack(stk, 12);

        System.out.printf("sum of stack elements: %d\n", getSumOfStack(stk, 12));

        System.out.printf("max element: %d\n\n", getMaxElementFromStack(stk, 12));

        System.out.println("stack before removing lower half");
        printStack(stk, 12);
        deleteLowerHalf(stk, 12);
        System.out.println("stack after removing lower half");
        printStack(stk, 12);

        System.out.println("stack before removing max element");
        printStack(stk, 12);
        removeMaxFromStack(stk, 12);
        System.out.println("stack after removing max element");
        printStack(stk, 12);

        System.out.println("stack before removing 88");
        printStack(stk, 12);
        removeElementFromStack(stk, 88, 12);
        System.out.println("stack after removing 88");
        printStack(stk, 12);

        System.out.println("stack before removing odds");
        printStack(stk, 12);
        removeOddsFromStack(stk, 12);
        System.out.println("stack after removing odds");
        printStack(stk, 12);

        /*ArrayStack<String> s = new ArrayStack<>(5);
        s.push("abc");
        s.push("abcvd");
        s.push("ab");
        s.push("abce");
        s.push("abcff");

        System.out.println(getMaxLengthStringFromStack(s, 5));
        */
    }

    public static String getMaxLengthStringFromStack(ArrayStack<String> stk, int size) {
        String max = stk.top();

        ArrayStack<String> tmp = new ArrayStack<>();
        while (!stk.isEmpty()) {
            tmp.push(stk.top());
            if (stk.top().length() > max.length()) {
                max = stk.top();
            }
            stk.pop();
        }

        return max;
    }

    public static void removeMaxFromStack(ArrayStack<Integer> stk, int size) {
        Integer max = getMaxElementFromStack(stk, size);
        removeElementFromStack(stk, max, size);
    }

    public static <T> void
    removeElementFromStack(ArrayStack stk, T element, int size) {

        ArrayStack tmp = new ArrayStack(size);
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

    public static void removeOddsFromStack(ArrayStack<Integer> stk, int size) {
        ArrayStack<Integer> evens = new ArrayStack<>(size);

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

    public static int getSumOfStack(ArrayStack<Integer> stk, int size) {
        int sum = 0;

        ArrayStack<Integer> tmp = new ArrayStack<>(size);

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

    public static void deleteLowerHalf(ArrayStack<Integer> stk, int size) {

        ArrayStack<Integer> tmp = new ArrayStack<>(size);

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

    public static Integer getMaxElementFromStack(ArrayStack<Integer> stk, int size) {
        Integer max = stk.top();

        ArrayStack<Integer> tmp = new ArrayStack<>(size);
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

    public static void printStack(ArrayStack<Integer> stk, int size) {
        ArrayStack<Integer> tmp = new ArrayStack<>(size);
        System.out.println("Stack elements!");
        while (!stk.isEmpty()) {
            System.out.println(stk.top());
            tmp.push(stk.top());
            stk.pop();
        }

        while (!tmp.isEmpty()) {
            stk.push(tmp.top());
            tmp.pop();
        }
        // additional line for some reason
        System.out.println();
    }

    public static void fillStack(ArrayStack<Integer> stk) {
        System.out.println("Enter stack elements");
        while (!stk.isFull()) {
            stk.push(read.nextInt());
        }
    }

}
