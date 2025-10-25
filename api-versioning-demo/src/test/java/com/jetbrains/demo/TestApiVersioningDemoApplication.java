package com.jetbrains.demo;

import org.springframework.boot.SpringApplication;

public class TestApiVersioningDemoApplication {

    public static void main(String[] args) {
        SpringApplication
                .from(ApiVersioningDemoApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }

}
