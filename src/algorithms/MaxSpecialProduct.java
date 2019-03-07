package algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * You are given an array A containing N integers.
 * The special product of each ith integer in this array is defined as the product of the following:
 * <p>
 * LeftSpecialValue: For an index i, it is defined as the index j such that A[j]>A[i] (i>j).
 * If multiple A[j]’s are present in multiple positions, the LeftSpecialValue is the maximum value of j.
 * <p>
 * RightSpecialValue: For an index i, it is defined as the index j such that A[j]>A[i] (j>i).
 * If multiple A[j]s are present in multiple positions, the RightSpecialValue is the minimum value of j.
 * <p>
 * Write a program to find the maximum special product of any integer in the array.
 * <p>
 * Input: You will receive array of integers as argument to function.
 * <p>
 * Return: Maximum special product of any integer in the array modulo 1000000007.
 * <p>
 * Note: If j does not exist, the LeftSpecialValue and RightSpecialValue are considered to be 0.
 * <p>
 * Constraints 1 <= N <= 10^5 1 <= A[i] <= 10^9
 */
public class MaxSpecialProduct {

    private static int maxSpecialProduct(List<Integer> A) {
        int[] left = new int[A.size()];
        int[] right = new int[A.size()];
        int len = A.size();

        for (int i = 0; i < len; i++) {
            left[i] = 0;
            right[i] = 0;
        }

        Stack<Integer> stack = new Stack<>();

        int cur_num, prev_index;
        int i;

        for (i = 0; i < len; i++) {
            cur_num = A.get(i);

            if (stack.empty()) {
                stack.push(i);
                continue;
            }

            prev_index = -1;

            while (true) {
                if (stack.empty()) {
                    break;
                }

                prev_index = stack.pop();

                if (A.get(prev_index) < cur_num) {
                    right[prev_index] = i;
                } else {
                    break;
                }
            }

            if (prev_index != -1) {
                if (A.get(prev_index) > cur_num) {
                    left[i] = prev_index;
                } else if (A.get(prev_index) == cur_num) {
                    left[i] = left[prev_index];
                }

                if (A.get(prev_index) >= cur_num) {
                    stack.push(prev_index);
                }
            }

            stack.push(i);
        }

        int max = Integer.MIN_VALUE, cur;

        for (i = 0; i < A.size(); i++) {
            cur = left[i] * right[i];
            if (cur > max) {
                max = cur;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] a = {5, 9, 6, 8, 6, 4, 6, 9, 5, 4, 9};

        List<Integer> A = Arrays.stream(a).boxed().collect(Collectors.toList());

        System.out.println(maxSpecialProduct(A));
    }

}
