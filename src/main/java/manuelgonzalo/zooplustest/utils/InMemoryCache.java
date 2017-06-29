package manuelgonzalo.zooplustest.utils;

import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.LRUMap;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.util.ArrayList;

/**
 * Created by manuel on 25/6/17.
 */
@ManagedResource(objectName = "ZooplusTest:name=InMemoryCache")
public class InMemoryCache<K, V> {
    private long timeToLive;
    private LRUMap lruMap;

    protected class InMemoryCacheObject {
        public long lastAccessed = System.currentTimeMillis();
        public V value;

        protected InMemoryCacheObject(V value) {
            this.value = value;
        }
    }

    public InMemoryCache(long timetoLiveMs, final long timerInterval, int maxItems) {
        this.timeToLive = timetoLiveMs * 1000;
        lruMap = new LRUMap(maxItems);

        if (timeToLive > 0 && timerInterval > 0) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(timerInterval * 1000);
                        } catch (InterruptedException ie) {

                        }
                        cleanup();
                    }
                }
            });

            t.setDaemon(true);
            t.start();
        }
    }

    @ManagedAttribute
    public long getTimeToLive() {
        return timeToLive;
    }

    @ManagedAttribute
    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void put(K key, V value) {
        synchronized (lruMap) {
            lruMap.put(key, new InMemoryCacheObject(value));
        }
    }

    @SuppressWarnings("unchecked")
    public V get(K key) {
        synchronized (lruMap) {
            InMemoryCacheObject c = (InMemoryCacheObject) lruMap.get(key);

            if (c == null)
                return null;
            else {
                c.lastAccessed = System.currentTimeMillis();
                return c.value;
            }
        }
    }

    public void remove(K key) {
        synchronized (lruMap) {
            lruMap.remove(key);
        }
    }

    public int size() {
        synchronized (lruMap) {
            return lruMap.size();
        }
    }

    @SuppressWarnings("unchecked")
    public void cleanup() {

        long now = System.currentTimeMillis();
        ArrayList<K> deleteKeyList = null;

        synchronized (lruMap) {
            MapIterator it = lruMap.mapIterator();

            deleteKeyList = new ArrayList<K>((lruMap.size() / 2) + 1);
            K key = null;
            InMemoryCacheObject c = null;

            while (it.hasNext()) {
                key = (K) it.next();
                c = (InMemoryCacheObject) it.getValue();

                if (c != null && (now > (timeToLive + c.lastAccessed))) {
                    deleteKeyList.add(key);
                }
            }
        }

        for (K key : deleteKeyList) {
            synchronized (lruMap) {
                lruMap.remove(key);
            }

            Thread.yield();
        }
    }
}
