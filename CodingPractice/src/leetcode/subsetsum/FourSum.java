package leetcode.subsetsum;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

/**
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums
 * such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 * <p>
 * Note:
 * <p>
 * The solution set must not contain duplicate quadruplets.
 * <p>
 * Example:
 * <p>
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 * <p>
 * A solution set is:
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 */
class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> list = new LinkedList<List<Integer>>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int sum = target - nums[i] - nums[j];
                int begin = j + 1;
                int end = nums.length - 1;
                while (begin < end) {
                    if (nums[begin] + nums[end] == sum) {
                        list.add(Arrays.asList(nums[i], nums[j], nums[begin], nums[end]));
                        begin++;
                        end--;
                    } else if (nums[begin] + nums[end] < sum) {
                        begin++;
                    } else {
                        end--;
                    }
                }
            }
        }

        Set set = new HashSet(list);
        list = new LinkedList(set);
        return list;
    }

    public static void main(String[] args) {
        FourSum fourSum = new FourSum();
        List<List<Integer>> output = fourSum.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        System.out.println(output);

        System.out.println(Integer.MAX_VALUE + Integer.MAX_VALUE);
        //Integer.MIN_VALUE
    }
}