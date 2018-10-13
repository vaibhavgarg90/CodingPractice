package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Generated all the permutations of given array
 */
public class Permutation {

    private static void generateAllPermutations(int[] arr, int index, List<List<Integer>> permutations) {
        if (index == arr.length) {
            return;
        }

        // first element
        if (index == 0) {
            List<Integer> startingPermutation = new ArrayList<>();
            startingPermutation.add(arr[0]);
            permutations.add(startingPermutation);
        }
        // any element except first
        else {
            int num = arr[index];
            List<List<Integer>> newPermutations = new ArrayList<>();
            for (List<Integer> permutation : permutations) {
                for (int i = permutation.size(); i >= 0; i--) {
                    List<Integer> newPermutation = new ArrayList<>(permutation);
                    newPermutation.add(i, num);
                    newPermutations.add(newPermutation);
                }
            }

            permutations.clear();

            permutations.addAll(newPermutations);
        }

        generateAllPermutations(arr, index + 1, permutations);
    }

    private static void printAllPermutations(int[] arr) {
        List<List<Integer>> permutations = new ArrayList<>();
        generateAllPermutations(arr, 0, permutations);
        System.out.println(permutations);
    }

    public static void main(String[] args) {
        printAllPermutations(new int[]{1, 2, 3});
    }
}
