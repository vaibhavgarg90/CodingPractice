package algorithms;

import java.util.ArrayList;

/**
 * You are given a binary string(i.e. with characters 0 and 1) S consisting of characters S1, S2, …, SN.
 * In a single operation, you can choose two indices L and R such that 1 ≤ L ≤ R ≤ N and flip the characters
 * SL, SL+1, …, SR. By flipping, we mean change character 0 to 1 and vice-versa.
 * <p>
 * Your aim is to perform ATMOST one operation such that in final string number of 1s is maximised.
 * If you don’t want to perform the operation, return an empty array. Else, return an array consisting of
 * two elements denoting L and R. If there are multiple solutions, return the lexicographically smallest
 * pair of L and R.
 * <p>
 * Notes:
 * <p>
 * Pair (a, b) is lexicographically smaller than pair (c, d) if a < c or, if a == c and b < d.
 * For example,
 * <p>
 * S = 010
 * <p>
 * Pair of [L, R] | Final string
 * _______________|_____________
 * [1 1]          | 110
 * [1 2]          | 100
 * [1 3]          | 101
 * [2 2]          | 000
 * [2 3]          | 001
 * <p>
 * We see that two pairs [1, 1] and [1, 3] give same number of 1s in final string. So, we return [1, 1].
 * Another example,
 * <p>
 * If S = 111
 * <p>
 * No operation can give us more than three 1s in final string. So, we return empty array [].
 *
 * @author vaibhav
 */
public class FlipBitsToMaximizeCount {

    private static ArrayList<Integer> flip(String A) {
        int max = 0;
        int cur = 0;
        int max_start = -1, max_end = -1, cur_start = -1, cur_end = -1;

        int n = A.length();
        int num1, num2, curSum;

        for (int i = 0; i < n; i++) {
            num1 = A.charAt(i) - '0';

            if (num1 == 0) {
                num2 = 1;
            } else {
                num2 = -1;
            }

            curSum = cur + num2;

            if (curSum >= num2) {
                cur = curSum;
                if (cur_start == -1) {
                    cur_start = i;
                }
                cur_end = i;
            } else {
                cur = num2;
                cur_start = cur_end = i;
            }

            if (cur > max) {
                max = cur;
                max_start = cur_start;
                max_end = cur_end;
            }
        }

        ArrayList<Integer> list = new ArrayList<>();

        if (max_start > -1) {
            list.add(max_start + 1);
            list.add(max_end + 1);
        }

        return list;
    }

    public static void main(String[] args) {
        System.out.println(flip("011100001111"));
    }

}
