package leetcode.subsetsum;

import java.util.*;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * Example:
 * <p>
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
class TwoSum {

    static int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        Map<Integer, Integer> numbersMap = new HashMap<>();

        int i;
        for (i = 0; i < length; i++) {
            numbersMap.put(new Integer(nums[i]), new Integer(i));
        }

        int[] output = new int[2];
        for (i = 0; i < length; i++) {
            Integer toCheck = new Integer(target - nums[i]);
            Integer index = numbersMap.get(toCheck);

            if ((index != null) && (index.intValue() != i)) {
                output[0] = i;
                output[1] = index.intValue();
                break;
            }
        }

        return output;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] output = twoSum.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(output[0] + " " + output[1]);
    }
}