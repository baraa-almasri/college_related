import linked_list.*;


public class FinalExamReview {

    public static void main(String []argv) {


        System.out.println(test(-2));

    }

    static int test(int num) {
        if (num == 0) {
            return 0;
        } else if (num > 100) {
            return -1;
        } else if (num < 0) {
            return -9;
        } else {
            return num + test(num - 1);
        }
    }


    static int feb(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return feb(n-1) + feb(n-2);
        }
    }

    static int pow(int n, int exp) {
        if (exp == 0) {
            return 1;

        } else if (exp == 1) {
            return n;

        } else {
            return pow(n, exp-1)*n;
        }
    }

    static int fact(int n) {
        if (n == 1) {
            return 1;
        } else {
            return fact(n-1)*n;
        }
    }


    static void print2ndHalf(RefSortedList<Integer> list) {

        int halfElements = list.size()/2;

        if (list.size() % 2 != 0) {
            halfElements++;
        }

        list.reset();

        boolean isHalf = false;
        int tmp = list.getNext();

        for (int i = 0; i < list.size(); i++) {
            if (i >= halfElements) {
                isHalf = true;
            }

            if (isHalf) {
                System.out.println(tmp);
            }

            tmp = list.getNext();
        }


    }

    static boolean checkLength(RefSortedList<String> list1, RefSortedList<Integer> list2) {
        if (list1.size() != list1.size()) {
            return false;
        }

        boolean lengthFlag = true;

        list1.reset();
        list2.reset();

        for (int i = 0; i < list1.size(); i++) {
            if (list1.getNext().length() != list2.getNext()) {
                lengthFlag = false;
            }
        }

        return lengthFlag;
    }

}
