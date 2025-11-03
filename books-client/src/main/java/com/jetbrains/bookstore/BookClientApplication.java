package com.jetbrains.bookstore;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookClientApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(BooksClient client) {
        return args -> {
            Books books = client.searchBooksWithV1("007");
            System.out.println("Books: " + books.books());

            books = client.searchBooksWithV2("007");
            System.out.println("Books: " + books.books());
        };
    }
}
