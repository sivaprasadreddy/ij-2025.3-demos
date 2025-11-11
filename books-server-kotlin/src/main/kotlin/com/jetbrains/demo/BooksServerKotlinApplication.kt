package com.jetbrains.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(BeansRegistrar::class)
class BooksServerKotlinApplication

fun main(args: Array<String>) {
    runApplication<BooksServerKotlinApplication>(*args)
}
