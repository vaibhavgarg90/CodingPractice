package leetcode.subsetsum;

public class KSubsetSum {

    private static Boolean[][] sumPresentInSubset;

    static boolean subsetHasSum(int[] arr, int sum, int index) {
        if (index >= arr.length) {
            return false;
        }

        if (arr[index] == sum) {
            return true;
        }

        return subsetHasSum(arr, sum - arr[index], index + 1) || subsetHasSum(arr, sum, index + 1);
    }

    static boolean checkSubsetSumWithoutDP(int[] arr, int sum) {
        return subsetHasSum(arr, sum, 0);
    }

    static boolean findSubsetHasSum(int[] arr, int sum, int index) {
        if ((index < 0) || (sum < 0)) {
            return false;
        }

        if (sum == 0) {
            return true;
        }

        if (sumPresentInSubset[sum][index] == null) {
            sumPresentInSubset[sum][index] = findSubsetHasSum(arr, sum - arr[index], index - 1)
                    || findSubsetHasSum(arr, sum, index - 1);
        }

        return sumPresentInSubset[sum][index];
    }

    static boolean checkSubsetSumWithDP(int[] arr, int sum) {
        sumPresentInSubset = new Boolean[sum + 1][arr.length];
        int length = arr.length;
        return findSubsetHasSum(arr, sum - arr[length - 1], length - 2) || findSubsetHasSum(arr, sum, length - 2);
    }

    public static void main(String[] args) {
        int arr[] = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println("Naive Solution:");
        for (int sum = 0; sum <= 50; sum++) {
            System.out.println(sum + "\t" + checkSubsetSumWithoutDP(arr, sum));
        }

        System.out.println("\nDynamic Programming Solution:");

        for (int sum = 0; sum <= 50; sum++) {
            System.out.println(sum + "\t" + checkSubsetSumWithDP(arr, sum));
        }
    }
}
