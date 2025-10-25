package com.jetbrains.demo;

import org.springframework.boot.SpringApplication;

public class TestModelFirstApplication {

    public static void main(String[] args) {
        SpringApplication
                .from(ModelFirstApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }

}
