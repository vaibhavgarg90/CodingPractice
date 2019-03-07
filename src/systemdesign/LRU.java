package systemdesign;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a LRU cache.
 */
public class LRU {

    private Map<Integer, Node> map;

    private List list;

    private int capacity;

    private int size;

    private class Node {
        int key;
        int val;
        Node prev;
        Node next;

        protected Node(int key, int val) {
            this(key, val, null, null);
        }

        private Node(int key, int val, Node prev, Node next) {
            this.key = key;
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    private class List {
        Node head;
        Node last;

        protected List() {
            this.head = this.last = null;
        }

        protected List(int key, int val) {
            this.head = this.last = new Node(key, val);
        }

        protected void setPointers(Node node, Node prev, Node next) {
            node.prev = prev;
            node.next = next;
        }

        protected Node insertFirst(int key, int val) {
            Node node = new Node(key, val);
            insertFirst(node);
            return node;
        }

        protected void insertFirst(Node node) {
            if (head == null) {
                head = node;
                if (last == null) {
                    last = node;
                }
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }
        }

        protected Node insertLast(int key, int val) {
            Node node = new Node(key, val);
            insertLast(node);
            return node;
        }

        protected void insertLast(Node node) {
            if (last == null) {
                last = node;
                if (head == null) {
                    head = node;
                }
            } else {
                last.next = node;
                node.prev = last;
                last = node;
            }
        }

        protected void remove(Node node) {
            // only one node
            if (head == last) {
                head = last = null;
                return;
            }

            // head is removed
            if (head == node) {
                head = head.next;
                head.prev = null;
                return;
            }

            // last is removed
            if (last == node) {
                last = last.prev;
                last.next = null;
                return;
            }

            // middle node removed
            node.prev.next = node.next;
            node.next.prev = node.prev;

            // set next and prev of node as null
            node.prev = node.next = null;
        }

        protected Node removeLast() {
            Node node = last;
            remove(last);
            return node;
        }

        protected void update(Node node, int val) {
            node.val = val;
            remove(node);
            insertFirst(node);
        }

        protected void print() {
            if (head == null) {
                return;
            }

            System.out.println("Head: " + head.val + "\t" + "Last: " + last.val);

            Node ptr = head;

            System.out.println("List Content:");

            while (ptr != null) {
                System.out.print(ptr.val + "\t");
                ptr = ptr.next;
            }

            System.out.println();
        }
    }

    private void print() {
        list.print();

        System.out.println("Map Content:");

        for (Map.Entry<Integer, Node> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().val);
        }

        System.out.println("Capacity: " + capacity + ", Size: " + size + "\n");
    }

    public LRU(int capacity) {
        this.map = new HashMap<>(capacity, 1f);
        this.list = new List();
        this.capacity = capacity;
        this.size = 0;
    }

    public int get(int key) {
        Node node = map.get(key);

        if (node == null) {
            print();
            return -1;
        }

        list.remove(node);

        list.insertFirst(node);

        print();

        return node.val;
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            list.update(node, value);
            map.remove(key);
            map.put(key, node);
            return;
        }

        if (this.size == this.capacity) {
            Node node = list.removeLast();
            map.remove(node.key);
            this.size--;
        }

        Node node = list.insertFirst(key, value);

        map.put(key, node);

        size++;

        print();
    }

    public static void main(String[] args) {
        LRU lru = new LRU(4);

        lru.set(5, 13);

        lru.set(9, 6);

        lru.set(4, 1);

        lru.get(4);

        lru.set(6, 1);

        lru.set(8, 11);

        lru.get(13);

        lru.get(1);

        lru.set(12, 12);

        lru.get(10);

        lru.set(15, 13);

        lru.set(2, 13);

        lru.set(7, 5);

        lru.set(10, 3);

        lru.get(6);

        lru.get(10);

        lru.set(15, 14);

        lru.set(5, 12);

        lru.get(5);

        lru.get(7);

        lru.get(15);

        lru.get(5);

        lru.get(6);

        lru.get(10);

        lru.set(7, 13);

        lru.get(14);

        lru.set(8, 9);

        lru.get(4);

        lru.set(6, 11);

        lru.get(9);

        lru.set(6, 12);

        lru.get(3);
    }
}
