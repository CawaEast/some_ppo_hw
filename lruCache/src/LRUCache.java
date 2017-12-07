import com.sun.tools.javac.util.Assert;

import java.util.HashMap;

/**
 * Created by Cawa on 16.09.2017.
 */
public class LRUCache {

    class Node {
        Node prev, next;
        String key, data;

        Node() {
            prev = this;
            next = this;
            data = "root";
        }

        Node(String k, String d) {
            prev = this;
            next = this;
            data = d;
            key = k;
        }


        void addNext(String key, String data) {
            addNext(new Node(key, data));
        }

        void addNext(Node other) {
            connect(other, next);
            connect(this, other);

            assert(next == other);
            assert(other.prev == this);
            assert(other.next.prev == other);
        }

        private void connect(Node prev, Node next) {
            prev.next = next;
            next.prev = prev;
        }

        void remove() {
            prev.next = next;
            next.prev = prev;
        }
/*
        public String toString() {
            return key + " : " + data;
        }*/

    }

    private final int DEFUAUT_MAX_SIZE = 100;

    private Node head;
    private int size;
    private HashMap<String, Node> map;
    private int maxSize;

    public LRUCache() {
        maxSize = DEFUAUT_MAX_SIZE;
        head = new Node();
        size = 0;
        map = new HashMap<String, Node>();
    }

    public LRUCache(int max) {
        maxSize = max;
        head = new Node();
        size = 0;
        map = new HashMap<String, Node>();
    }

    public void put(String key, String data) {
        if (contains(key)) {
            touch(key);
        } else {
            if (map.size() == maxSize) {
                removeFirst();
            }
            assert map.size() < maxSize : "Limit of data is reached, failed on the oldest data removing.";
            last().addNext(key, data);
            map.put(key, last());
            size++;
        }
        assert last().key.equals(key) : "Failed on putting new data. Key: " + key;
    }

    public void remove(String key) {
        if (contains(key)) {
            remove(map.get(key));
        }
        assert contains(key) : "Failed on removing data. Key: " + key;
    }

    public String get(String key) {
        if (contains(key)) {
            return map.get(key).data;
        } else  {
            return "";
        }
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node first() {
        return head.next;
    }

    private Node last() {
        return head.prev;
    }

    private void removeFirst() {
        remove(first());
    }

    private void remove(Node node) {
        assert node.equals(head) : "We can't remove fake root.";
        node.remove();
        map.remove(node.data);
        size--;
    }

    private void touch(String key) {
        if (contains(key)) {
            Node node = map.get(key);
            node.remove();
            last().prev.addNext(node);
        }
    }
}
