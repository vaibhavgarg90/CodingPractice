package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements various utility methods such as:
 * - Does addition/multiplication of two numbers result in overflow/underflow?
 * - All primes numbers less than or equal to a specified number.
 * - Is the given number prime?
 * - Can a number be expressed as power of two numbers?
 * - Does the given number belong to fibonacci series?
 * <p>
 *
 * @author vaibhav
 */
public class Util {

    private static final int MIN_INT_VALUE = Integer.MIN_VALUE;
    private static final int MAX_INT_VALUE = Integer.MAX_VALUE;
    private static final int MAX_INT_HAVING_SQUARE = (int) Math.sqrt(MAX_INT_VALUE);

    /**
     * @param a, b : values that need to be added
     * @return true iff addition of a and b will result in overflow/underflow
     */
    public static boolean willAdditionOverflow(int a, int b) {
        if (a > 0 && b > 0) {
            return a > MAX_INT_VALUE - b;
        } else if (a < 0 && b < 0) {
            return a < MIN_INT_VALUE - b;
        }

        return false;
    }

    /**
     * @param a, b : values that need to be multiplied
     * @return true iff multiplication of a and b will result in overflow/underflow
     */
    public static boolean willMultiplicationOverflow(int a, int b) {
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

    /**
     * @param val         : value that needs to be tested for primality
     * @param lowerPrimes : all the primes lower than val
     * @return true iff val is prime
     */
    public static boolean isPrime(int val, ArrayList<Integer> lowerPrimes) {
        for (Integer num : lowerPrimes) {
            int x = Integer.valueOf(num);
            if (val % x == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * @param val : number below which all primes need to be returned
     * @return all the prime integers below num
     */
    public static ArrayList<Integer> allLowerPrimes(int val) {
        ArrayList<Integer> primes = new ArrayList<>();

        if (val < 2) {
            return primes;
        } else if (val < 3) {
            primes.add(2);
            return primes;
        } else if (val < 5) {
            primes.add(2);
            primes.add(3);
            return primes;
        } else {
            primes.add(2);
            primes.add(3);

            int k = 1;

            while (true) {
                // check whether 6*k overflows
                if (willMultiplicationOverflow(6, k))
                    break;

                int x = 6 * k;

                // check whether 6k-1 is greater than val
                if ((x - 1) > val) {
                    break;
                }

                // check whether 6k-1 is prime
                if (isPrime(x - 1, primes)) {
                    primes.add(x - 1);
                }

                // check whether 6k+1 overflows or greater than val
                if ((willAdditionOverflow(x, 1)) || ((x + 1) > val))
                    break;

                // check whether 6k+1 is prime
                if (isPrime(x + 1, primes)) {
                    primes.add(x + 1);
                }

                k++;
            }

            return primes;
        }
    }

    public static boolean isPrime(Integer val) {
        if (val < 2) {
            return false;
        } else if (val == 2 || val == 3) {
            return true;
        } else {
            // check if val is divisible by 2.
            if ((val % 2) == 0)
                return false;

            // if val is not in the form of 6k-1 or 6k+1, it is not prime.
            if (((val % 6) != 1) && ((val % 6) != 5))
                return false;

            // primality check
            int sqrt = (int) Math.sqrt(val);

            for (int i = 3; i < sqrt; i += 2) {
                if ((val % i) == 0)
                    return false;
            }

            return true;
        }
    }

    /**
     * @param val : the number to be checked
     * @return true iff an integer can be expressed as exponent of two numbers
     */
    public static boolean canBeExpressedAsPower(Integer val) {
        Map<Integer, Boolean> powers = new HashMap<>();
        powers.put(1, true);

        for (int i = 2; i <= MAX_INT_HAVING_SQUARE; i++) {
            long num = i;

            for (int j = 2; j < 32; j++) {
                num = num * i;

                if (num > (long) MAX_INT_VALUE) {
                    break;
                }

                Integer x = (int) num;
                powers.put(x, true);
            }
        }

        return powers.containsKey(val);
    }

    /**
     * @param val : the number to be checked
     * @return true iff a number falls in fibonacci series
     */
    public static boolean isFibonacciNumber(Integer val) {
        Map<Integer, Boolean> fibonacciNumbers = new HashMap<>();
        int a = 1, b = 2;

        fibonacciNumbers.put(a, true);
        fibonacciNumbers.put(b, true);

        int c;

        while (!willAdditionOverflow(a, b)) {
            c = a + b;
            fibonacciNumbers.put(c, true);
            a = b;
            b = c;
        }

        return fibonacciNumbers.containsKey(val);
    }

    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        for (int item : arr) {
            System.out.println(item);
        }
    }

    public static void printArray(int[] arr, String separator) {
        if (arr == null || arr.length == 0) {
            return;
        }

        for (int item : arr) {
            System.out.print(item + separator);
        }
    }

    public static void main(String[] args) {
        System.out.println(canBeExpressedAsPower(1024000000));
        System.out.println(isFibonacciNumber(12345678));
        System.out.println(isPrime(2147483647));
    }
}
