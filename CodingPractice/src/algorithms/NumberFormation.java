package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a set of digits (A) in sorted order, find how many numbers of length B are possible
 * whose value is less than number C.
 * <p>
 * NOTE: All numbers can only have digits from the given set.
 * <pre>
 * Examples:
 * 	Input:
 * 	  0 1 5
 * 	  1
 * 	  2
 * 	Output:
 * 	  2 (0 and 1 are possible)
 *
 * 	Input:
 * 	  0 1 2 5
 * 	  2
 * 	  21
 * 	Output:
 * 	  5 (10, 11, 12, 15, 20 are possible)
 *
 * Constraints:
 *     1 <= B <= 9, 0 <= C <= 1e9 & 0 <= A[i] <= 9
 * </pre>
 */
public class NumberFormation {

    private static int solve(ArrayList<Integer> A, int B, int C) {
        int start = 0, end = 0;

        if (B == 1) {
            start = 0;
            end = 9;
        } else if (B == 2) {
            start = 10;
            end = 99;
        } else if (B == 3) {
            start = 100;
            end = 999;
        } else if (B == 4) {
            start = 1000;
            end = 9999;
        } else if (B == 5) {
            start = 10000;
            end = 99999;
        } else if (B == 6) {
            start = 100000;
            end = 999999;
        } else if (B == 7) {
            start = 1000000;
            end = 9999999;
        } else if (B == 8) {
            start = 10000000;
            end = 99999999;
        } else if (B == 9) {
            start = 100000000;
            end = 999999999;
        }

        if (C < start) {
            return 0;
        }

        if (C < end) {
            end = C - 1;
        }

        Set<Character> dict = new HashSet<>();
        for (int a : A) {
            char ch = Character.forDigit(a, 10);
            dict.add(ch);
        }

        int count = 0;

        for (int num = start; num <= end; num++) {
            String str = String.valueOf(num);
            boolean exists = true;

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (!dict.contains(ch)) {
                    exists = false;
                    break;
                }
            }

            if (exists) {
                System.out.println(num + " exists");
                count++;
            } else {
                System.out.println(num + " does not exist");
            }
        }

        return count;
    }

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>();
        A.add(0);
        A.add(2);
        A.add(6);
        A.add(8);
        A.add(9);

        System.out.println(solve(A, 3, 1278));
    }
}
