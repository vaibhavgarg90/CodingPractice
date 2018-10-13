package linkedlist;

/**
 * Represents a node in the linked list.
 */
public class ListNode {

    public int val;

    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    @Override
    public String toString() {
        return "" + val;
    }
}
