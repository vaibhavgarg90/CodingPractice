package linkedlist;

/**
 * Sort linked list using insertion sort.
 */
public class ListInsertionSort {

    private static ListNode insertionSortList(ListNode A) {
        if (A == null || A.next == null) {
            return A;
        }

        ListNode head = A;
        ListNode prev = head;
        ListNode next = null;
        A = A.next;

        while (A != null) {
            next = A.next;

            if (A.val < prev.val) {
                // check head
                if (A.val < head.val) {
                    prev.next = next;
                    A.next = head;
                    head = A;
                }
                // start with head, go right unless and until correct position is found
                else {
                    ListNode ptr = head;
                    while (A.val > ptr.next.val) {
                        ptr = ptr.next;
                    }
                    // insert node after ptr
                    prev.next = next;
                    ListNode tmp = ptr.next;
                    ptr.next = A;
                    A.next = tmp;
                }
            } else {
                prev = A;
            }

            A = next;
        }

        prev.next = null;

        return head;
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

        ListNode head = insertionSortList(A);

        System.out.println(head);
    }
}
