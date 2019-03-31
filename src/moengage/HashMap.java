package moengage;

import java.util.ArrayList;
import java.util.List;

/**
 * A custom hash map implementation that supports following:
 * <ul>
 * <li>put</li>
 * <li>get</li>
 * <li>print</li>
 * </ul>
 *
 * @param <K> the key type
 * @param <V> the value type
 * @author vaibhav
 */
public class HashMap<K, V> {

    /**
     * the node of the hash map
     */
    class Node {

        /**
         * the key of the node
         */
        private K key;

        /**
         * the value of the node
         */
        private V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * @return the key
         */
        public K getKey() {
            return this.key;
        }

        /**
         * @return the value
         */
        public V getValue() {
            return this.value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    /**
     * the default loading factor
     */
    private final float LOADING_FACTOR;

    /**
     * the default sizer of the map
     */
    private int SIZE;

    /**
     * the nodes in the hash map
     * collision is resolved by chaining
     */
    private List<List<Node>> nodes;

    /**
     * the current number of nodes present in the hash map
     */
    private int nodeCount;

    HashMap() {
        this(0.75f, 15);
    }

    HashMap(float loadingFactor, int defaultSize) {
        this.LOADING_FACTOR = loadingFactor;
        this.SIZE = defaultSize;

        this.nodes = new ArrayList<>();
        this.nodeCount = 0;

        initialize(this.nodes, this.SIZE);
    }

    /**
     * puts an entry into the hash map
     *
     * @param key   the key
     * @param value the value
     */
    void put(K key, V value) {
        if (!canInsert()) {
            increaseCapacity();
        }

        insert(key, value);
        nodeCount++;
    }

    /**
     * @param key the key of interest
     * @return the value of the key
     */
    V get(K key) {
        return getValue(key);
    }

    /**
     * remove an entry with key k in the hash map
     *
     * @param key the key of interest
     */
    void remove(K key) {
        int index = getKeyIndex(key);

        List<Node> nodes = this.nodes.get(index);

        Node node = getNodeForKey(nodes, key);

        if (node == null) {
            throw new NullPointerException("Node not found");
        }

        nodes.remove(node);
    }

    /**
     * initialize the nodes in the hash map by null values
     */
    private void initialize(List<List<Node>> nodes, int size) {
        for (int i = 0; i < size; i++) {
            nodes.add(new ArrayList<>());
        }
    }

    /**
     * @return @code true iff a new value can be inserted in the hash map
     */
    private boolean canInsert() {
        return ((this.nodeCount / this.SIZE) < this.LOADING_FACTOR);
    }

    /**
     * increments the capacity of the hash map
     */
    private void increaseCapacity() {
        int newSize = ((SIZE * 2) - 1);
        List<List<Node>> newNodes = new ArrayList<>(newSize);

        initialize(newNodes, newSize);

        for (int i = 0; i < this.SIZE; i++) {
            List<Node> nodes = this.nodes.get(i);

            if ((nodes != null) && !nodes.isEmpty()) {
                for (Node node : nodes) {
                    insert(node.getKey(), node.getValue(), newNodes, newSize);
                }
            }
        }

        this.SIZE = newSize;
        this.nodes = newNodes;
    }

    /**
     * returns the index at which the key needs to be inserted
     *
     * @param key the key of interest
     * @return the index of the key
     */
    private int getKeyIndex(K key) {
        if (key == null) {
            return 0;
        }

        return getKeyIndex(key, this.SIZE);
    }

    /**
     * returns the index at which the key needs to be inserted
     *
     * @param key  the key of interest
     * @param size the size of the hash map
     * @return the index of the key
     */
    private int getKeyIndex(K key, int size) {
        if (key == null) {
            return 0;
        }

        if (size == 0) {
            throw new IllegalArgumentException("The size of the map can not be zero");
        }

        return (key.hashCode() % size);
    }

    /**
     * @return the value corresponding to @param key
     */
    private V getValue(K key) {
        int index = getKeyIndex(key);

        List<Node> nodes = this.nodes.get(index);

        if (nodes == null || nodes.isEmpty()) {
            throw new NullPointerException("Key not found");
        }

        Node node = null;

        for (Node curNode : nodes) {
            if (curNode.getKey().equals(key)) {
                node = curNode;
                break;
            }
        }

        if (node == null) {
            throw new NullPointerException("Key not found");
        }

        return node.getValue();
    }

    /**
     * insert a value in the hash map
     *
     * @param key   the key to be inserted
     * @param value the value to be inserted
     */
    private void insert(K key, V value) {
        insert(key, value, this.nodes, this.SIZE);
    }

    /**
     * @param key   the key to be inserted
     * @param value the value to be inserted
     * @param nodes the array in which the values are to be inserted
     */
    private void insert(K key, V value, List<List<Node>> nodes, int size) {
        if (nodes == null || size == 0) {
            throw new IllegalArgumentException("Invalid values passed");
        }

        int index = getKeyIndex(key, size);

        insert(key, value, nodes, size, index);
    }

    /**
     * insert a value in the hash map
     *
     * @param key   the key to be inserted
     * @param value the value to be inserted
     * @param nodes the array in which the node is to be inserted
     * @param index the index at which to be inserted
     */
    private void insert(K key, V value, List<List<Node>> nodes, int size, int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid values passed");
        }

        List<Node> curNodes = nodes.get(index);

        if (curNodes == null) {
            curNodes = new ArrayList<>();
            nodes.set(index, curNodes);
        }

        Node existingNode = getNodeForKey(curNodes, key);

        if (existingNode != null) {
            existingNode.setValue(value);
        } else {
            Node node = new Node(key, value);
            curNodes.add(node);
        }
    }

    /**
     * get the node for the specified key, else @code null
     *
     * @param nodes the nodes at an index in the hash map
     * @param key   the key of interest
     * @return the node for the specified key, else @code null
     */
    private Node getNodeForKey(List<Node> nodes, K key) {
        if (nodes == null || nodes.isEmpty()) {
            return null;
        }

        for (Node node : nodes) {
            if (node.getKey().equals(key)) {
                return node;
            }
        }

        return null;
    }

    /**
     * print the content of the map
     */
    private void print() {
        for (int i = 0; i < this.SIZE; i++) {
            List<Node> nodes = this.nodes.get(i);

            if (nodes != null) {
                for (Node node : nodes) {
                    if (node != null) {
                        System.out.println(node.getKey() + " :: " + node.getValue());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "a");
        map.print();
        System.out.println();

        map.put(2, "b");
        map.print();
        System.out.println();

        map.put(1, "c");
        map.print();
        System.out.println();

        System.out.println(map.get(1));
        System.out.println();

        System.out.println(map.get(2));
        System.out.println();

        map.remove(1);
        map.print();
        System.out.println();
    }
}
