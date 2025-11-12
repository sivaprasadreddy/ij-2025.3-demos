package com.jetbrains.demo.api;

import com.jetbrains.demo.domain.Book;
import com.jetbrains.demo.domain.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
class BookController {
    private final BookRepository bookRepository;

    BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    Books getAll() {
        List<Book> books = bookRepository.findAll();
        return Books.of(books);
    }

    @GetMapping("/{isbn}")
    ResponseEntity<Book> getByIsbn(@PathVariable String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    record Books(List<Book> books){
        public static Books of(List<Book> books) {
            return new Books(books);
        }
    }
}
