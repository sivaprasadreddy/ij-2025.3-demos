package com.jetbrains.demo;

import org.springframework.boot.SpringApplication;

public class TestSingleQueryLoadingApplication {

    public static void main(String[] args) {
        SpringApplication
                .from(SingleQueryLoadingApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }

}
