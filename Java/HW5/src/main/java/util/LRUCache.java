package util;

import entity.Song;

import java.util.HashMap;

public class LRUCache {
    Node head = new Node(), tail = new Node();
    HashMap<String, Node> map = new HashMap();
    private int capacity;

    public Node getHead() {
        return head;
    }

    public HashMap<String, Node> getMap() {
        return map;
    }

    public LRUCache(int _capacity) {
        capacity = _capacity;
        head.next = tail;
        tail.prev = head;
    }

    public Song get(String key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            insert(node);
            return node.value;
        } else {
            throw new RuntimeException("CANNOT FIND SONG");
        }
    }

    public void set(String key, Song value) {
        if(map.containsKey(key)) {
            remove(map.get(key));
        }
        if(map.size() == capacity) {
            remove(tail.prev);
        }
        insert(new Node(key, value));
    }

    private void remove(Node node) {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insert(Node node){
        map.put(node.key, node);
        Node headNext = head.next;
        head.next = node;
        node.prev = head;
        headNext.prev = node;
        node.next = headNext;
    }

    public class Node{
        Node prev;
        Node next;
        String key;
        Song value;
        Node(){};
        Node(String _key, Song _value) {
            key = _key;
            value = _value;
        }

        public Node getPrev() {
            return prev;
        }

        public Node getNext() {
            return next;
        }

        public String getKey() {
            return key;
        }

        public Song getValue() {
            return value;
        }
    }
}
