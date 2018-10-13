package algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Find the running median of an array.
 * <p>
 * Example:
 * <p>
 * Input:
 * [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
 * <p>
 * Output:
 * [1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5, 5.5]
 *
 * @author vaibhav
 */
public class RunningMedian {

    private static void addNumber(int num, PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
        if (lowers.isEmpty() || num < lowers.peek()) {
            lowers.add(num);
        } else {
            highers.add(num);
        }
    }

    private static double getMedian(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
        PriorityQueue<Integer> bigger = lowers.size() > highers.size() ? lowers : highers;
        PriorityQueue<Integer> smaller = lowers.size() > highers.size() ? highers : lowers;

        if (bigger.size() - smaller.size() > 1) {
            smaller.add(bigger.poll());
        }

        // equal size
        if (lowers.size() == highers.size()) {
            double left = (double) lowers.peek();
            double right = (double) highers.peek();
            return (left + right) / 2;
        }
        // lowers has 1 extra element
        else if (lowers.size() > highers.size()) {
            return (double) lowers.peek();
        }
        // highers has 1 extra element
        else {
            return (double) highers.peek();
        }

    }

    private static List<Double> runningMedian(int[] arr) {
        PriorityQueue<Integer> lowers = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> highers = new PriorityQueue<>();
        List<Double> medians = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            addNumber(arr[i], lowers, highers);
            medians.add(getMedian(lowers, highers));
        }

        return medians;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(runningMedian(arr));
    }
}
