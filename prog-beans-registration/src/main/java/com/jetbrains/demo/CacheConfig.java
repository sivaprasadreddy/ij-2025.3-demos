package com.jetbrains.demo;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CacheManagerRegistrar.class)
public class CacheConfig {
}
