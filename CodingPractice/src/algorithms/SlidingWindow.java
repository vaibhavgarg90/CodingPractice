package algorithms;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array and an integer k, for each contiguous sub-array of size k:
 * find the maximum, minimum and the sum of maximum and minimum elements.
 * <p>
 * Input:
 * arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}
 * k = 3
 * Output:
 * Maximum:
 * 3 3 4 5 5 5 6
 * Minimum:
 * 1 1 1 1 2 2 2
 * Sum:
 * 4 4 5 6 7 7 8
 *
 * @author vaibhav
 */
public class SlidingWindow {

    private static void printMaximum(int[] arr, int k) {
        Deque<Integer> Q = new LinkedList<>();
        int i;

        // process first k elements
        for (i = 0; i < k; i++) {
            while (!Q.isEmpty() && arr[i] >= arr[Q.peekLast()]) {
                Q.removeLast();
            }

            Q.addLast(i);
        }

        while (i < arr.length) {
            // print element for previous window
            System.out.print(arr[Q.peekFirst()] + "\t");

            // remove elements outside the window
            while (!Q.isEmpty() && Q.peek() <= (i - k)) {
                Q.removeFirst();
            }

            // remove elements that are not required in the Q
            while (!Q.isEmpty() && arr[i] >= arr[Q.peekLast()]) {
                Q.removeLast();
            }

            // add current element to the Q
            Q.addLast(i);

            i++;
        }

        // print element for the last window
        System.out.print(arr[Q.peekFirst()] + "\t");
    }

    private static void printMinimum(int[] arr, int k) {
        Deque<Integer> Q = new LinkedList<>();
        int i;

        // process first k elements
        for (i = 0; i < k; i++) {
            while (!Q.isEmpty() && arr[i] <= arr[Q.peekLast()]) {
                Q.removeLast();
            }

            Q.addLast(i);
        }

        while (i < arr.length) {
            // print element for previous window
            System.out.print(arr[Q.peekFirst()] + "\t");

            // remove elements outside the window
            while (!Q.isEmpty() && Q.peek() <= (i - k)) {
                Q.removeFirst();
            }

            // remove elements that are not required in the Q
            while (!Q.isEmpty() && arr[i] <= arr[Q.peekLast()]) {
                Q.removeLast();
            }

            // add current element to the Q
            Q.addLast(i);

            i++;
        }

        // print element for the last window
        System.out.print(arr[Q.peekFirst()] + "\t");
    }

    private static void printMinMaxSum(int[] arr, int k) {
        Deque<Integer> maxQ = new LinkedList<>();
        Deque<Integer> minQ = new LinkedList<>();
        int i;

        for (i = 0; i < k; i++) {
            while (!maxQ.isEmpty() && (arr[i] >= arr[maxQ.peekLast()])) {
                maxQ.removeLast();
            }

            while (!minQ.isEmpty() && (arr[i] <= arr[minQ.peekLast()])) {
                minQ.removeLast();
            }

            maxQ.addLast(i);
            minQ.addLast(i);
        }

        while (i < arr.length) {
            System.out.print(arr[maxQ.peekFirst()] + arr[minQ.peekFirst()] + "\t");

            while (!maxQ.isEmpty() && (maxQ.peekFirst() <= (i - k))) {
                maxQ.removeFirst();
            }

            while (!minQ.isEmpty() && (minQ.peekFirst() <= (i - k))) {
                minQ.removeFirst();
            }

            while (!maxQ.isEmpty() && (arr[i] >= arr[maxQ.peekLast()])) {
                maxQ.removeLast();
            }

            while (!minQ.isEmpty() && (arr[i] <= arr[minQ.peekLast()])) {
                minQ.removeLast();
            }

            maxQ.addLast(i);
            minQ.addLast(i);

            i++;
        }

        System.out.print(arr[maxQ.peekFirst()] + arr[minQ.peekFirst()] + "\t");
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        int k = 3;

        System.out.println("Maximum:");
        printMaximum(arr, k);
        System.out.println("\n\nMinimum:");
        printMinimum(arr, k);
        System.out.println("\n\nSum:");
        printMinMaxSum(arr, k);
    }
}
