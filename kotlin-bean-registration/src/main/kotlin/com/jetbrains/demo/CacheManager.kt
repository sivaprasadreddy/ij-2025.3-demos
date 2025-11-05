package com.jetbrains.demo

interface CacheManager {
    fun put(key: String, value: Any)
    fun get(key: String): Any?
    fun clearCache()
}