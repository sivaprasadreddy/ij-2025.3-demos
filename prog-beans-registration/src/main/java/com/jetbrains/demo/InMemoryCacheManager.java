package com.jetbrains.demo;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCacheManager implements CacheManager {
    private final Map<String, Object> cache = new HashMap<>();

    @Override
    public void put(String key, Object value) {
        cache.put(key, value);
    }

    @Override
    public Object get(String key) {
        return cache.get(key);
    }

    @Override
    public void clearCache() {
        cache.clear();
    }
}
