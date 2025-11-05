package com.jetbrains.demo

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class KotlinBeanRegistrationApplication {
    @Bean
    fun runner(cacheManager: CacheManager): ApplicationRunner {
        return ApplicationRunner { args: ApplicationArguments ->
            cacheManager.put("foo", "bar")
            println(cacheManager.get("foo"))
        }
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinBeanRegistrationApplication>(*args)
}
