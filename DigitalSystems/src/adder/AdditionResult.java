package adder;

public class AdditionResult {
    public int sum = 0b0;
    public int carry = 0b0;

    public AdditionResult(int sum, int carry) {
        this.sum = sum;
        this.carry = carry;
    }

    @Override
    public String toString() {
        return String.format("{sum: %d, carry: %d}", this.sum, this.carry);
    }
}
