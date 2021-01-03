package adder;

public class Adder {

    public AdditionResult halfAdd(int a, int b) {

        return new AdditionResult(a ^ b, a & b);
    }

    public AdditionResult fullAdd(int a, int b, int c) {
        // add a to b using a half adder
        //var aPlusB = this.halfAdd(a, b);
        // take XOR of the three inputs
        //var finalSum = aPlusB.sum ^ c;
        // take of first and second carry
        //int cary1ORcarry2 = aPlusB.carry | (b & c);

        var aXORb = a ^ b;
        var aXORbXORc = aXORb ^ c;
        var aANDb = a & b;
        var aXORbANDc = aXORb & c;


        return new AdditionResult(aXORbXORc, aANDb | aXORbANDc);
    }

    private int countDigits(int x){
        int digits = 0;
        while(x > 1){
            x /= 10;
            digits++;
        }

        return digits ;
    }
}
