package linkedlist;

public class ListMergeSort {

    private static ListNode merge(ListNode A, int lCount, ListNode B, int rCount) {
        if (A == null) {
            return B;
        }

        if (B == null) {
            return A;
        }

        ListNode prev = new ListNode(0);
        ListNode last = prev;

        int l = 0;
        int r = 0;

        while (l <= lCount && r <= rCount) {
            if (A.val <= B.val) {
                last.next = A;
                last = A;
                A = A.next;
                l++;
            } else {
                last.next = B;
                last = B;
                B = B.next;
                r++;
            }
        }

        while (l <= lCount) {
            last.next = A;
            last = A;
            A = A.next;
            l++;
        }

        while (r <= rCount) {
            last.next = B;
            last = B;
            B = B.next;
            r++;
        }

        last.next = null;

        return prev.next;
    }

    private static ListNode mergeSort(ListNode A, int low, int high) {
        if (low == high) {
            return A;
        }

        int mid = (low + high) >>> 1;

        ListNode left = mergeSort(A, low, mid);

        ListNode right = A;

        for (int i = low; i <= mid; i++) {
            right = right.next;
        }

        right = mergeSort(right, mid + 1, high);

        return merge(left, mid - low + 1, right, high - mid);
    }

    private static ListNode mergeSortList(ListNode A) {
        if (A == null || A.next == null) {
            return A;
        }

        int count = 0;

        ListNode tmp = A;

        while (tmp != null) {
            count++;
            tmp = tmp.next;
        }

        return mergeSort(A, 0, count - 1);
    }

    public static void main(String[] args) {
        ListNode A = new ListNode(5);
        ListNode last = A;

        ListNode tmp;

        tmp = new ListNode(66);
        last.next = tmp;
        last = tmp;

        tmp = new ListNode(68);
        last.next = tmp;
        last = tmp;

        tmp = new ListNode(42);
        last.next = tmp;
        last = tmp;

        tmp = new ListNode(73);
        last.next = tmp;
        last = tmp;

        tmp = new ListNode(25);
        last.next = tmp;
        last = tmp;

        tmp = new ListNode(84);
        last.next = tmp;
        last = tmp;

        tmp = new ListNode(63);
        last.next = tmp;
        last = tmp;

        tmp = new ListNode(72);
        last.next = tmp;
        last = tmp;

        tmp = new ListNode(20);
        last.next = tmp;
        last = tmp;

        tmp = new ListNode(77);
        last.next = tmp;
        last = tmp;

        ListNode head = mergeSortList(A);

        System.out.println(head);
    }
}
