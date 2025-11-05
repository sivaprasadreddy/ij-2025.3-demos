package com.jetbrains.demo

import org.springframework.beans.factory.BeanRegistrarDsl

class CacheManagerRegistrar: BeanRegistrarDsl({

    registerBean("cacheManager") {
        getCacheManagerInstance()
    }

})

const val CACHE_IMPL_CLASS = "CACHE_IMPL_CLASS"
const val CAFFEINE_CACHE_CLASS = "com.github.benmanes.caffeine.cache.Cache"

fun getCacheManagerInstance(): CacheManager {
    try {
        val className = determineCacheImplClassName()
        val clazz = Class.forName(className)
        return clazz.getDeclaredConstructor().newInstance() as CacheManager
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}

fun determineCacheImplClassName(): String {
    val cacheImpl = System.getenv(CACHE_IMPL_CLASS)
    if (cacheImpl != null && !cacheImpl.trim { it <= ' ' }.isEmpty()) {
        return cacheImpl
    }
    if (isCaffineOnClasspath()) {
        return CAFFEINE_CACHE_CLASS
    }
    return InMemoryCacheManager::class.java.getName()
}

fun isCaffineOnClasspath(): Boolean {
    try {
        Class.forName(CAFFEINE_CACHE_CLASS)
        return true
    } catch (_: ClassNotFoundException) {
        return false
    }
}
