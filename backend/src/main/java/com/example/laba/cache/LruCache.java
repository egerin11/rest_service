package com.example.laba.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * The type Lru cache.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public class LruCache<K, V> {
  private final int capacity;
  private Map<K, V> cache;
  private LinkedList<K> keys;

  /**
   * Instantiates a new Lru cache.
   *
   * @param size the size
   */
  public LruCache(int size) {
    this.capacity = size;
    this.cache = new HashMap<>();
    this.keys = new LinkedList<>();
  }

  /**
   * Put.
   *
   * @param key the key
   * @param value the value
   */
  public void put(K key, V value) {
    if (cache.containsKey(key)) {
      keys.remove(key);
    } else if (cache.size() >= capacity) {
      K oldestKey = keys.removeFirst();
      cache.remove(oldestKey);
    }

    cache.put(key, value);
    keys.addLast(key);
  }

  /**
   * Get v.
   *
   * @param key the key
   * @return the v
   */
  public V get(K key) {
    if (cache.containsKey(key)) {
      keys.remove(key);
      keys.addLast(key);
      return cache.get(key);
    }
    return null;
  }

  /**
   * Remove.
   *
   * @param key the key
   */
  public void remove(K key) {
    if (cache.containsKey(key)) {
      keys.remove(key);
      cache.remove(key);
    }
  }
}
