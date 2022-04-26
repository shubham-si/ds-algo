package misc;

import java.util.concurrent.ConcurrentHashMap;

public final class LRUCache<K, V> {

    /**
     * A doubly-linked-list implementation to save objects into the hashmap
     * as key-value pair.
     *
     * @author sunil
     *
     * @param <K>
     * @param <V>
     */
    private static class Node<K, V> {
        private V value;
        private K key;
        private Node<K, V> next, prev;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    /**
     * The maximum number of elements that can be cached, should be set during instantiation
     * time.
     */
    private final int maxCapacity;

    /**
     * Use {@linkplain ConcurrentHashMap} here to maintain the cache of objects.
     * Also this offers thread safe access of the cache.
     */
    private ConcurrentHashMap<K, Node<K, V>> map;

    /**
     * A key-value representation of the cache object identified by a cache key.
     * This is actually a doubly-linked list which maintains the most recently accessed
     * objects (read/write) at the tail-end and the least read objects at the head.
     */
    private Node<K, V> head, tail;





    public LRUCache(int maxCapacity) {
        this(16, maxCapacity);
    }

    public LRUCache(int initialCapacity, int maxCapacity) {
        this.maxCapacity = maxCapacity;
        if (initialCapacity > maxCapacity)
            initialCapacity = maxCapacity;
        map = new ConcurrentHashMap<>(initialCapacity);
    }

    /**
     * Removes a node from the head position doubly-linked list.
     * @param node
     */
    private void removeNode(Node<K, V> node) {
        if (node == null)
            return;

        if (node.prev != null) {    // if not head node
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {    // if not tail node
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }

    /**
     * Offers a node to the tail-end of the doubly-linked list because
     * it was recently read or written.
     * @param node
     */
    private void offerNode(Node<K, V> node) {
        if (node == null)
            return;
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head.next;
            node.prev = null;
            head = node;
        }
    }

    /**
     * Adds a new object to the cache. If the cache size has reached it's capacity,
     * then the least recently accessed object will be evicted.
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        if (map.contains(key)) {
            Node<K, V> node = map.get(key);
            node.value = value;
            removeNode(node);
            offerNode(node);
        } else {
            if (map.size() == maxCapacity) {
                System.out.println("maxCapacity of cache reached");
                map.remove(head.key);
                removeNode(head);
            }

            Node<K, V> node = new Node<K, V>(key, value);
            offerNode(node);
            map.put(key, node);
        }
    }

    /**
     * Fetches an object from the cache (could be null if no such mapping exists).
     * If the object is found in the cache, then it will be moved to the tail-end of the
     * doubly-linked list to indicate that it was recently accessed.
     *
     * @param key
     */
    public V get(K key) {
        Node<K, V> node = map.get(key);
        removeNode(node);
        offerNode(node);
        return node != null ? node.value : null;
    }

    /**
     * Utility function to print the cache objects.
     */
    public void printCache() {
        Node<K, V> curr = head;
        while (curr != null) {
            System.out.print(curr.value + " -> ");
            curr = curr.next;
        }
        System.out.println();
    }

    /**
     * Runner program to test the LRU cache
     * @param args
     */
    public static void main(String[] args) {

/**
 * 1. create LRUCache of initial capacity 10
 * 2. insert 10 objects to cache
 * 3. print the cache objects
 * 4. access the first object and print the cache
 * 5. insert new objects to cache
 * 6. print the cache and observe that the least recently used
 *    objects are evicted
 */


// 1. initiate the cache with capacity 10
        LRUCache<String, String> cache = new LRUCache<String, String>(10);

// 2. insert 10 objects to cache
        for(int i=1; i<=10; i++) {
            cache.put(String.format("key-%d",  i), String.format("value-%d",  i));
        }

// 3. print the cache objects
        System.out.println("printing cache:");
        cache.printCache();

// 4. access the first object and print the cache
        cache.get("key-1");
        System.out.println("printing cache after accessing key-1:");
        cache.printCache();

// 5. insert new objects to cache
        for(int i=11; i<=15; i++) {
            cache.put(String.format("key-%d",  i), String.format("value-%d",  i));
        }

// 6. print the cache and observe that the least recently used objects are evicted
        System.out.println("printing cache after adding new objects:");
        cache.printCache();

    }

}
/*
public class LRUCache<K, V>{

    // Define Node with pointers to the previous and next items and a key, value pair
    class Node<T, U> {
        Node<T, U> previous;
        Node<T, U> next;
        T key;
        U value;

        public Node(Node<T, U> previous, Node<T, U> next, T key, U value){
            this.previous = previous;
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<K, Node<K, V>> cache;
    private Node<K, V> leastRecentlyUsed;
    private Node<K, V> mostRecentlyUsed;
    private int maxSize;
    private int currentSize;

    public LRUCache(int maxSize){
        this.maxSize = maxSize;
        this.currentSize = 0;
        leastRecentlyUsed = new Node<K, V>(null, null, null, null);
        mostRecentlyUsed = leastRecentlyUsed;
        cache = new HashMap<K, Node<K, V>>();
    }

    public V get(K key){
        Node<K, V> tempNode = cache.get(key);
        if (tempNode == null){
            return null;
        }
        // If MRU leave the list as it is
        else if (tempNode.key == mostRecentlyUsed.key){
            return mostRecentlyUsed.value;
        }

        // Get the next and previous nodes
        Node<K, V> nextNode = tempNode.next;
        Node<K, V> previousNode = tempNode.previous;

        // If at the left-most, we update LRU
        if (tempNode.key == leastRecentlyUsed.key){
            nextNode.previous = null;
            leastRecentlyUsed = nextNode;
        }

        // If we are in the middle, we need to update the items before and after our item
        else if (tempNode.key != mostRecentlyUsed.key){
            previousNode.next = nextNode;
            nextNode.previous = previousNode;
        }

        // Finally move our item to the MRU
        tempNode.previous = mostRecentlyUsed;
        mostRecentlyUsed.next = tempNode;
        mostRecentlyUsed = tempNode;
        mostRecentlyUsed.next = null;

        return tempNode.value;

    }

    public void put(K key, V value){
        if (cache.containsKey(key)){
            return;
        }

        // Put the new node at the right-most end of the linked-list
        Node<K, V> myNode = new Node<K, V>(mostRecentlyUsed, null, key, value);
        mostRecentlyUsed.next = myNode;
        cache.put(key, myNode);
        mostRecentlyUsed = myNode;

        // Delete the left-most entry and update the LRU pointer
        if (currentSize == maxSize){
            cache.remove(leastRecentlyUsed.key);
            leastRecentlyUsed = leastRecentlyUsed.next;
            leastRecentlyUsed.previous = null;
        }

        // Update cache size, for the first added entry update the LRU pointer
        else if (currentSize < maxSize){
            if (currentSize == 0){
                leastRecentlyUsed = myNode;
            }
            currentSize++;
        }
    }
}
 */