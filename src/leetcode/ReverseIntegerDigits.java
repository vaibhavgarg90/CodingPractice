package leetcode;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 * <p>
 * Example 1:
 * Input: 123
 * Output: 321
 * </p>
 * <p>
 * Example 2:
 * Input: -123
 * Output: -321
 * </p>
 * <p>
 * Example 3:
 * Input: 120
 * Output: 21
 * </p>
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range:
 * [−2^31,  2^31 − 1].
 * <p>
 * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class ReverseIntegerDigits {

    private static final int MIN_INT_VALUE = Integer.MIN_VALUE;
    private static final int MAX_INT_VALUE = Integer.MAX_VALUE;

    private boolean willMultiplicationOverflow(int a, int b) {
        if (a == 0 || b == 0) {
            return false;
        }
        // both a and b are greater than 0
        if (a > 0 && b > 0) {
            return a > (MAX_INT_VALUE / b);
        }
        // both a and b are less than 0
        else if (a < 0 && b < 0) {
            return a < (MAX_INT_VALUE / b);
        }
        // exactly one out of a or b is negative.
        // check if multiplication under-flows.
        else if (b > 0) {
            return a < (MIN_INT_VALUE / b);
        }
        // a is greater than 0
        else {
            return b < (MIN_INT_VALUE / a);
        }
    }

    private boolean willAdditionOverflow(int a, int b) {
        if (a > 0 && b > 0) {
            return a > MAX_INT_VALUE - b;
        } else if (a < 0 && b < 0) {
            return a < MIN_INT_VALUE - b;
        }
        return false;
    }

    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }

        if (x == Integer.MIN_VALUE) {
            return 0;
        }

        int num = x;
        int output = 0;

        while (num != 0) {
            if (willMultiplicationOverflow(output, 10)) {
                return 0;
            }

            output *= 10;
            int mod = num % 10;

            if (willAdditionOverflow(output, mod)) {
                return 0;
            }

            output += mod;
            num = num / 10;
        }

        if (((x < 0) && (output > 0)) || ((x > 0) && (output < 0))) {
            return 0;
        }

        return output;
    }

    public static void main(String[] args) {
        ReverseIntegerDigits reverseIntegerDigits = new ReverseIntegerDigits();
        System.out.println(reverseIntegerDigits.reverse(-1234567891));
        System.out.println(reverseIntegerDigits.reverse(214748312));
    }
}
