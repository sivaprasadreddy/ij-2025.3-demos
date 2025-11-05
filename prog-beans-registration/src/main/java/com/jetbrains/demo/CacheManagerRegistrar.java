package com.jetbrains.demo;


import org.springframework.beans.factory.BeanRegistrar;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.core.env.Environment;

public class CacheManagerRegistrar implements BeanRegistrar {
    public static final String CACHE_IMPL_CLASS = "CACHE_IMPL_CLASS";
    public static final String CAFFEINE_CACHE_CLASS = "com.github.benmanes.caffeine.cache.Cache";

    @Override
    public void register(BeanRegistry registry, Environment env) {
        registry.registerBean(CacheManager.class,
                (spec) -> {
                    CacheManager bean = getCacheManagerInstance();
                    spec.supplier((s) -> bean);
                });
    }

    private CacheManager getCacheManagerInstance() {
        try {
            String className = determineCacheImplClassName();
            Class<?> clazz = Class.forName(className);
            return (CacheManager) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String determineCacheImplClassName() {
        String cacheImpl = System.getenv(CACHE_IMPL_CLASS);
        if (cacheImpl != null && !cacheImpl.trim().isEmpty()) {
            return cacheImpl;
        }
        if( isCaffineOnClasspath()) {
            return CAFFEINE_CACHE_CLASS;
        }
        return InMemoryCacheManager.class.getName();
    }

    private boolean isCaffineOnClasspath() {
        try {
            Class.forName(CAFFEINE_CACHE_CLASS);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
