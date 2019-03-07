package algorithms;

import java.util.*;

/**
 * Sub-array problem solutions.
 *
 * @author vaibhav
 */
public class SubArraySum {

    /**
     * Given an array of integers, find length of the largest sub-array with sum equals to 0.
     * Examples :
     * <p>
     * Input: arr[] = {15, -2, 2, -8, 1, 7, 10, 23};
     * Output: 5
     * The largest sub-array with 0 sum is -2, 2, -8, 1, 7
     * <p>
     * Input: arr[] = {1, 2, 3}
     * Output: 0
     * There is no sub-array with 0 sum
     * <p>
     * Input: arr[] = {1, 0, 3}
     * Output: 1
     */
    private static List<Integer> findLargestSubArrayWithZeroSum(List<Integer> A) {
        Map<Integer, Integer> sums = new HashMap<>();

        int start = -1, end = -1;
        int i, sum = 0;

        for (i = 0; i < A.size(); i++) {
            sum += A.get(i);

            if (sum == 0) {
                start = 0;
                end = i;
                continue;
            }

            Integer index = sums.get(sum);

            if (index != null) {
                int curStart = index + 1;
                int curEnd = i;

                if (start != -1) {
                    if ((curEnd - curStart) > (end - start)) {
                        start = curStart;
                        end = curEnd;
                    }
                } else {
                    start = curStart;
                    end = curEnd;
                }
            } else {
                sums.put(sum, i);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();

        if (start > -1) {
            while (start <= end) {
                list.add(A.get(start));
                start++;
            }
        }

        return list;
    }

    /**
     * Given an array containing only 0s and 1s, find the largest sub-array
     * which contain equal no of 0s and 1s.
     * <p>
     * Examples:
     * <p>
     * Input: arr[] = {1, 0, 1, 1, 1, 0, 0}
     * Output: 1 to 6 (Starting and Ending indexes of output sub-array)
     * <p>
     * Input: arr[] = {1, 1, 1, 1}
     * Output: No such sub-array
     * <p>
     * Input: arr[] = {0, 0, 1, 1, 0}
     * Output: 0 to 3 Or 1 to 4
     */
    private static int findLargestSubArrayWithEqualZerosAndOnes(int[] arr) {
        List<Integer> mod_arr_list = new ArrayList<>();
        int i;

        for (i = 0; i < arr.length; i++) {
            if ((arr[i] == 0) || (arr[i] == 1)) {
                mod_arr_list.add(arr[i]);
            }
        }

        //System.out.print("Array of 0s and 1s: ");

        int[] mod_arr = new int[mod_arr_list.size()];
        i = 0;
        for (int a : mod_arr_list) {
            System.out.print(a + " ");
            mod_arr[i] = (a == 0) ? -1 : 1;
            i++;
        }

        System.out.println();

        return 0;
    }

    /**
     * Find the largest sub-array having largest sum.
     * Kadane's algorithm
     */
    private static int largestSumSubArray(int[] arr) {
        int maxSum = 0;
        int maxSumEndingHere = 0;

        for (int i = 0; i < arr.length; i++) {
            maxSumEndingHere = Math.max(arr[i], maxSumEndingHere + arr[i]);

            if (maxSumEndingHere > maxSum) {
                maxSum = maxSumEndingHere;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(-19);
        list.add(8);
        list.add(2);
        list.add(-8);
        list.add(19);
        list.add(-2);
        list.add(2);
        list.add(0);
        list.add(1);
        list.add(0);
        list.add(1);
        list.add(0);
        list.add(1);
        list.add(0);
        list.add(1);
        list.add(-23);

        int[] arr = list.stream().mapToInt(i -> i).toArray();

        System.out.println("Largest sub-array with zero sum:");
        System.out.println(findLargestSubArrayWithZeroSum(list));

        System.out.println("\nLargest sub-array with equal number of 0s and 1s:");
        findLargestSubArrayWithEqualZerosAndOnes(arr);

        System.out.println("\nLargest sub-array sum:");
        System.out.println(largestSumSubArray(arr));
    }
}
