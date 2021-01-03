package adder;

import adder.Adder;

public class Driver {
    public static void main(String[] argv) {
        var adder = new Adder();
        int a = 0b1;
        int b = 0b1;
        int c = 0b1;
        /*
         *   110
         * + 011
         *   ----
         *   1001
         *
         * half add 0, 1
         * full add 1, 1, 0
         * full add 0, 1, 1
         * put carry in the last digit
         */

        var ha0 = adder.halfAdd(0, 1);
        var fa0 = adder.fullAdd(1, 1, ha0.carry);
        var fa1 = adder.fullAdd(1, 0, fa0.carry);
        System.out.println(fa1);
        System.out.printf("\n%d%d%d%d\n", fa1.carry, fa1.sum, fa0.sum, ha0.sum);
    }
}
