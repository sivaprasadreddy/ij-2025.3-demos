package com.jetbrains.bookstore;

import org.springframework.boot.SpringApplication;

public class TestBookServerApplication {

    public static void main(String[] args) {
        SpringApplication
                .from(BookServerApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }

}
