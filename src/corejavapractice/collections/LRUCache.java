package corejavapractice.collections;

import java.util.HashMap;

public class LRUCache {
    private final int capacity;
    private final HashMap<Integer, Node> map;
    private final Node head;
    private final Node tail;

    // Node class for the doubly linked list
    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0); // Dummy head
        this.tail = new Node(0, 0); // Dummy tail
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            insertToFront(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            remove(node);
            insertToFront(node);
        } else {
            if (map.size() >= capacity) {
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            insertToFront(newNode);
        }
    }

    // Remove a node from the linked list
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Insert a node at the front of the linked list
    private void insertToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
        public static void main(String[] args) {
            LRUCache cache = new LRUCache(2); // Create an LRUCache with capacity 2

            // Test put operations
            cache.put(1, 1); // Cache is {1=1}
            cache.put(2, 2); // Cache is {1=1, 2=2}

            // Test get operation
            System.out.println(cache.get(1)); // Returns 1, Cache is {2=2, 1=1}

            // Test updating cache with a new entry
            cache.put(3, 3); // Evicts key 2, Cache is {1=1, 3=3}
            System.out.println(cache.get(2)); // Returns -1 (not found)

            // Test updating existing key
            cache.put(4, 4); // Evicts key 1, Cache is {3=3, 4=4}
            System.out.println(cache.get(1)); // Returns -1 (not found)
            System.out.println(cache.get(3)); // Returns 3
            System.out.println(cache.get(4)); // Returns 4
        }
    

}
