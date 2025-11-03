package com.jetbrains.bookstore;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Optional;

@HttpExchange(url = "http://localhost:8080")
interface BooksClient {

    @GetExchange(url = "/api/books")
    Books getAll();

    @GetExchange(url = "/api/books/{isbn}")
    Optional<Book> getByIsbn(String isbn);

    @GetExchange(url = "/api/books/search", version = "1.0")
    Books searchBooksWithV1(@RequestParam("q") String query);

    @GetExchange(url = "/api/books/search", version = "2.0")
    Books searchBooksWithV2(@RequestParam("q") String query);
}
