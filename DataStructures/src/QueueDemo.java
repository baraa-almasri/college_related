import queue.*;

import java.util.Scanner;

public class QueueDemo {

    public static final Scanner read = new Scanner(System.in);

    public static void main(String []argv) {
        ArrayBndQueue<Integer> q = new ArrayBndQueue<>();
        q.enqueue(9);
        q.enqueue(-5);
        q.enqueue(7);
        q.enqueue(-8);

        System.out.printf("max element in queue: %d\n", getMaxElementFromQueue(q));
        System.out.printf("9 found: %b\n", containsInQueue(q, 9));
        //removeNegativesFromQueue(q);

        printQueue(q, 4);

    }

    public static <T> void printQueue(ArrayBndQueue<T> q, int size) {
        ArrayBndQueue<T> tmp = new ArrayBndQueue<>(size);
        while (!q.isEmpty()) {
            T tmpElement = q.dequeue();
            System.out.println(tmpElement);
            tmp.enqueue(tmpElement);
        }

        while (!tmp.isEmpty()) {
            q.enqueue(tmp.dequeue());
        }
    }

    public static <T> void readQueue(ArrayBndQueue<T> q) {
        while (!q.isFull()) {
            q.enqueue((T)read.next());
        }
    }

    public static <T> boolean containsInQueue(ArrayBndQueue<T> q, T element) {
        ArrayBndQueue<T> tmp = new ArrayBndQueue<>(q.getSize());
        boolean found = false;
        while (!q.isEmpty()) {
            T front = q.dequeue();
            if (front.equals(element)) {
                found = true;
            }
            tmp.enqueue(front);
        }

        while (!tmp.isEmpty()) {
            q.enqueue(tmp.dequeue());
        }

        return found;
    }

    public static int getMaxElementFromQueue(ArrayBndQueue<Integer> q) {
        ArrayBndQueue<Integer> tmp = new ArrayBndQueue<>(q.getSize());
        int max = q.dequeue();
        tmp.enqueue(max);

        while (!q.isEmpty()) {
            int front = q.dequeue();
            if (front > max) {
                max = front;
            }
            tmp.enqueue(front);
        }

        while (!tmp.isEmpty()) {
            q.enqueue(tmp.dequeue());
        }

        return max;
    }

    public static void removeNegativesFromQueue(ArrayBndQueue<Integer> q) {
        ArrayBndQueue<Integer> positives = new ArrayBndQueue<>(q.getSize());

        int n;
        while (!q.isEmpty()) {
            n = q.dequeue();
            if (n > 0) {
                positives.enqueue(n);
            }
        }

        while (!positives.isEmpty()) {
            q.enqueue(positives.dequeue());
        }
    }


}