import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Cawa on 30.09.2017.
 */
class LRUCacheTest {
    @Test
    void put() {
        LRUCache cache = new LRUCache();
        cache.put("a", "aData");
        cache.put("b", "bData");
    }

    @Test
    void contains() {
        LRUCache cache = new LRUCache(5);
        cache.put("a", "aData");
        cache.put("b", "bData");
        assert cache.contains("a") : "Put, but doesnt contains, test.";
        assert !cache.contains("c") : "Didnt put, but contains, test.";
    }

    @Test
    void remove() {
        LRUCache cache = new LRUCache();
        cache.put("a", "aData");
        cache.put("b", "bData");
        cache.remove("a");
        assert cache.contains("b") : "Put, doesnt remove, but doesnt contains, test.";
        assert cache.contains("a") : "Put, removed, but contains, test.";
    }

    @Test
    void get() {
        LRUCache cache = new LRUCache();
        cache.put("a", "aData");
        cache.put("b", "bData");
        String ans = cache.get("a");
        assert ans.equals("aData") : "Put, but cant get, test.";
        ans = cache.get("qwe");
        assert ans.equals("") : "Didn't put, but can get, test.";
    }

    @Test
    void size() {
        LRUCache cache = new LRUCache(2);
        int ans = cache.size();
        Assert.assert ans == 0 : "Empty, but size = " + cache.size() + ", test.";
        cache.put("a", "aData");
        cache.put("b", "bData");
        assert cache.size() == 2 : "Size should be 2, but size = " + cache.size() + ", test.";
        cache.put("a", "aData1");
        cache.put("b", "bData1");
        assert cache.size() == 2 : "Size should be 2, but size = " + cache.size() + ", test.";
        cache.put("c", "cData");
        cache.put("d", "dData");
        assert cache.size() == 2 : "Size should be 2,overflow check , but size = " + cache.size() + ", test.";
    }

    @Test
    void isEmpty() {
        LRUCache cache = new LRUCache();
        boolean ans = cache.isEmpty();
        assert ans : "Empty, but doesnt know it, test.";
        cache.put("a", "aData");
        ans = cache.isEmpty();
        assert !ans : "Not empty, but doesnt know it, test.";
        cache.remove("a");
        assert cache.isEmpty() : "All elemets removed, but doesnt know it, test.";
    }

}