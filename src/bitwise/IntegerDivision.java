package bitwise;

public class IntegerDivision {

    private static int divide(int A, int B) {
        long dividend = (long) A;
        long divisor = (long) B;
        long sign = ((dividend < 0l) ^ (divisor < 0l)) ? -1l : 1l;

        System.out.println(sign);

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        long quotient = 0, temp = 0;

        for (int i = 31; i >= 0; --i) {
            if (temp + (divisor << i) <= dividend) {
                temp += divisor << i;
                quotient |= (long) 1 << i;
            }
        }

        System.out.println(quotient);

        long output = sign * quotient;

        System.out.println(output);

        long max = (long) Integer.MAX_VALUE;

        return (output > max) ? Integer.MAX_VALUE : (int) output;
    }

    public static void main(String[] args) {
        System.out.println(divide(-2147483648, -1));
    }
}
