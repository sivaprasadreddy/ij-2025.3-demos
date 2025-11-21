package com.jetbrains.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProgrammaticBeansRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProgrammaticBeansRegistrationApplication.class, args);
    }

    @Bean
    ApplicationRunner runner() {
        return args -> {

        };
    }
}