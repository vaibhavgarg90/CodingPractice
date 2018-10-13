package algorithms;

/**
 * Given an array of integers. Find a peak element in it.
 * An array element is peak if it is NOT smaller than its neighbors.
 * For corner elements, we need to consider only one neighbor.
 * For example, for input array {5, 10, 20, 15}, 20 is the only peak element.
 * For input array {10, 20, 15, 2, 23, 90, 67}, there are two peak elements: 20 and 90.
 * Note that we need to return any one peak element.
 */
public class PeakElement {

    private static int findPeakIndex(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = (left + right) >>> 1;

            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{10, 20, 15, 28, 21, 24, 25, 26};

        int index = findPeakIndex(arr);

        System.out.println("Peak index = " + index + ", Peak Element = " + arr[index]);
    }
}
