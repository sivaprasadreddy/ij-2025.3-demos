package com.jetbrains.demo;

public interface CacheManager {
    void put(String key, Object value);
    Object get(String key);
    void clearCache();
}
