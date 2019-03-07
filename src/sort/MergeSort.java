package sort;

import util.Util;

/**
 * Implementation of Merge Sort
 */
class MergeSort {

    private static void merge(int arr[], int l, int mid, int r) {
        if (l == r) {
            return;
        }

        int lCount = mid - l + 1;
        int rCount = r - mid;


        int[] L = new int[lCount];
        int[] R = new int[rCount];

        int i, j, k;

        j = l;
        for (i = 0; i < lCount; i++) {
            L[i] = arr[j];
            j++;
        }

        j = mid + 1;
        for (i = 0; i < rCount; i++) {
            R[i] = arr[j];
            j++;
        }

        i = 0;
        j = 0;
        k = l;

        while (k <= r) {
            if (i == lCount) {
                arr[k] = R[j];
                j++;
            } else if (j == rCount) {
                arr[k] = L[i];
                i++;
            } else if (R[j] < L[i]) {
                arr[k] = R[j];
                j++;
            } else {
                arr[k] = L[i];
                i++;
            }

            k++;
        }
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }

        int mid = (l + r) >>> 1;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    // Driver method
    public static void main(String args[]) {
        int[] arr = new int[]{12, 11, 13, 5, 6, 7};

        System.out.println("Given Array");
        Util.printArray(arr);

        mergeSort(arr, 0, arr.length - 1);

        System.out.println("\nSorted array");
        Util.printArray(arr);
    }
}