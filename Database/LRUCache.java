import java.util.HashMap;
public class LRUCache {
   public static void main(String[] args) {
       LRUCache lru = new LRUCache(2);
       lru.set('a',1);
       lru.set('b',2);
       lru.set('c',3);
       System.out.println(lru.head.next.key=='c');
       lru.get('b');
       System.out.println(lru.head.next.key=='b');
       
    }
  //hashmap retrives data in O(1),linkedlist munipulate data in O(1)
  Node head = new Node(), tail = new Node();
  HashMap<Character, Node> map = new HashMap();
  int capacity;
  
  public LRUCache(int _capacity) {
    capacity = _capacity;
    head.next = tail;
    tail.prev = head;
  }

  public Integer get(Character key) {
    if(map.containsKey(key)) {
      Node node = map.get(key);
      remove(node);
      insert(node);
      return node.value;
    } else {
      return -1;
    }
  }

  public void set(Character key, int value) {
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
  
  class Node{
    Node prev, next;
    Character key;
    Integer value;
    Node(){};
    Node(Character _key, Integer _value) {
      key = _key;
      value = _value;
    }
  }
}