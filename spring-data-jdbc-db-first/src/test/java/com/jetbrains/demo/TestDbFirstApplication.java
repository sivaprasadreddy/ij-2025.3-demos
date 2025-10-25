package com.jetbrains.demo;

import org.springframework.boot.SpringApplication;

public class TestDbFirstApplication {

    public static void main(String[] args) {
        SpringApplication
                .from(DbFirstApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }

}
