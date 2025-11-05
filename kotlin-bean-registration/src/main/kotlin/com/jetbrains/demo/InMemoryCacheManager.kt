package com.jetbrains.demo


class InMemoryCacheManager : CacheManager {
    private val cache: MutableMap<String, Any> = HashMap<String, Any>()

    override fun put(key: String, value: Any) {
        cache[key] = value
    }

    override fun get(key: String): Any? {
        return cache[key]
    }

    override fun clearCache() {
        cache.clear()
    }
}