import java.util.*;

class MultiLevelCacheSystem<K, V> {
    
    private final CacheLevel<K, V> l1, l2, l3;
    
    // 評分函數，用於排序 CacheNode
    private static Comparator<CacheNode> getComparator() {
        return (n1, n2) -> {
            long score1 = n1.accessCount;
            long score2 = n2.accessCount;
            return Long.compare(score1, score2); // 簡化：僅根據訪問次數
        };
    }

    public MultiLevelCacheSystem() {
        // 容量和成本可以根據實際需求調整
        this.l1 = new CacheLevel<>(2, 1, 1, getComparator());
        this.l2 = new CacheLevel<>(5, 3, 2, getComparator());
        this.l3 = new CacheLevel<>(10, 10, 3, getComparator());
    }
    
    public V get(K key) {
        CacheNode<K, V> node;

        // 檢查L1
        node = l1.get(key);
        if (node != null) {
            // 處理升級
            return node.value;
        }

        // 檢查L2
        node = l2.get(key);
        if (node != null) {
            // 從L2升級到L1
            l2.remove(key);
            l1.put(key, node.value);
            return node.value;
        }

        // 檢查L3
        node = l3.get(key);
        if (node != null) {
            // 從L3升級到L2
            l3.remove(key);
            l2.put(key, node.value);
            return node.value;
        }

        return null; // 未找到
    }

    public void put(K key, V value) {
        CacheNode<K, V> oldNode = l1.get(key);
        if (oldNode != null) {
            oldNode.value = value;
            return;
        }

        oldNode = l2.get(key);
        if (oldNode != null) {
            oldNode.value = value;
            return;
        }

        oldNode = l3.get(key);
        if (oldNode != null) {
            oldNode.value = value;
            return;
        }

        // 全新節點，從L1開始放入
        l1.put(key, value);
    }
    
    // 省略了CacheLevel和CacheNode的完整實現，這裡只展示其主要邏輯
}

// --------------------------------------------------------------------------------

class CacheLevel<K, V> {
    private final int capacity;
    private final int level;
    private final Map<K, CacheNode<K, V>> cacheMap = new HashMap<>();
    private final PriorityQueue<CacheNode<K, V>> evictionHeap;

    public CacheLevel(int capacity, int cost, int level, Comparator<CacheNode> comparator) {
        this.capacity = capacity;
        this.level = level;
        this.evictionHeap = new PriorityQueue<>(comparator);
    }

    public CacheNode<K, V> get(K key) {
        CacheNode<K, V> node = cacheMap.get(key);
        if (node != null) {
            node.accessCount++;
            node.lastAccessTime = System.currentTimeMillis();
            // 在實際實現中，需要更新heap中的節點位置
        }
        return node;
    }
    
    public void put(K key, V value) {
        if (cacheMap.size() >= capacity) {
            CacheNode<K, V> evictedNode = evictionHeap.poll();
            cacheMap.remove(evictedNode.key);
            // 這裡應該將evictedNode移動到下一層
            // 簡化: 這裡不處理層級間的移動
        }
        
        CacheNode<K, V> newNode = new CacheNode<>(key, value, level);
        cacheMap.put(key, newNode);
        evictionHeap.offer(newNode);
    }

    public void remove(K key) {
        CacheNode<K, V> node = cacheMap.remove(key);
        if (node != null) {
            evictionHeap.remove(node);
        }
    }
}

// --------------------------------------------------------------------------------

class CacheNode<K, V> {
    K key;
    V value;
    int accessCount = 0;
    long lastAccessTime = System.currentTimeMillis();
    int level;
    
    public CacheNode(K key, V value, int level) {
        this.key = key;
        this.value = value;
        this.level = level;
    }
}