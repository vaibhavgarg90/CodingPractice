package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubSequence {

    private static void findIncreasingSubSequencesFromIndex(int[] arr, int index, List<List<Integer>> increasingSubSequences) {
        int number = arr[index];

        if (index == arr.length - 1) {
            List<Integer> subSequenceWithSingleElement = new ArrayList<>();
            subSequenceWithSingleElement.add(number);
            increasingSubSequences.add(subSequenceWithSingleElement);
            return;
        }

        findIncreasingSubSequencesFromIndex(arr, index + 1, increasingSubSequences);

        boolean isLarger = true;

        List<List<Integer>> increasingSubSequencesToAdd = new ArrayList<>();

        for (List<Integer> increasingSubSequence : increasingSubSequences) {
            if (number < increasingSubSequence.get(0)) {
                List<Integer> increasingSubSequenceToAdd = new ArrayList<>();
                increasingSubSequenceToAdd.add(number);
                increasingSubSequenceToAdd.addAll(increasingSubSequence);
                increasingSubSequencesToAdd.add(increasingSubSequenceToAdd);
                isLarger = false;
            }
        }

        if (isLarger) {
            List<Integer> subSequenceWithSingleElement = new ArrayList<>();
            subSequenceWithSingleElement.add(number);
            increasingSubSequencesToAdd.add(subSequenceWithSingleElement);
        }

        increasingSubSequences.addAll(increasingSubSequencesToAdd);
    }

    private static List<Integer> findLIS(int[] arr) {
        if ((arr == null) || (arr.length == 0)) {
            return new ArrayList<>();
        }

        List<List<Integer>> increasingSubSequences = new ArrayList<>();

        findIncreasingSubSequencesFromIndex(arr, 0, increasingSubSequences);

        System.out.println(increasingSubSequences);

        int maxLength = -1;
        int index = -1;

        for (int i = 0; i < increasingSubSequences.size(); i++) {
            List<Integer> increasingSubSequence = increasingSubSequences.get(i);
            int size = increasingSubSequence.size();
            if (size > maxLength) {
                maxLength = size;
                index = i;
            }
        }

        return increasingSubSequences.get(index);
    }

    public static void main(String[] args) {
        System.out.println(findLIS(new int[]{1, 2, 5, 4, 16, 10, 7}));
    }
}
