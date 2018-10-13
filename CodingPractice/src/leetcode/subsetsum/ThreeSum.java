package leetcode.subsetsum;

import java.util.*;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Note:
 * <p>
 * The solution set must not contain duplicate triplets.
 * <p>
 * Example:
 * <p>
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        int length = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int i, j;
        Integer toCheck;
        List<Integer> indices;

        for (i = 0; i < length; i++) {
            toCheck = new Integer(nums[i]);
            Integer index = new Integer(i);

            if (map.containsKey(toCheck)) {
                map.get(toCheck).add(index);
            } else {
                indices = new ArrayList<>();
                indices.add(index);
                map.put(toCheck, indices);
            }
        }

        List<List<Integer>> triplets = new ArrayList<>();

        Integer lastChecked = null;

        for (i = 0; i < length - 2; i++) {
            Integer num_i = new Integer(nums[i]);
            if ((lastChecked != null) && (nums[i] == lastChecked.intValue())) {
                continue;
            }

            for (j = i + 1; j < length - 1; j++) {
                if ((lastChecked != null) && (nums[j] == lastChecked.intValue())) {
                    continue;
                }

                Integer num_j = new Integer(nums[j]);
                toCheck = new Integer(-1 * (num_i.intValue() + num_j.intValue()));
                indices = map.get(toCheck);

                if (indices != null) {
                    for (int k = 0; k < indices.size(); k++) {
                        int index = indices.get(k).intValue();
                        if ((index > i) && (index > j)) {
                            List<Integer> triplet = new ArrayList<>();
                            triplet.add(num_i);
                            triplet.add(num_j);
                            triplet.add(toCheck);
                            triplets.add(triplet);
                            break;
                        }
                    }
                }

                lastChecked = num_j;
            }

            lastChecked = num_i;
        }

        return triplets;
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> output = threeSum.threeSum(new int[]{0, -4, -1, -4, -2, -3, 2});
        System.out.println(output);
    }
}