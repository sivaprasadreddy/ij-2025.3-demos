package com.jetbrains.bookstore;

import org.springframework.data.domain.Sort;
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
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Book> books = bookRepository.findAll(sort);
        return Books.of(books);
    }

    @GetMapping("/{isbn}")
    ResponseEntity<Book> getByIsbn(@PathVariable String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/search", version = "1.0")
    Books searchBooks(@RequestParam("q") String query) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(query);
        return Books.of(books);
    }

    @GetMapping(value = "/search", version = "2.0")
    Books searchBooks_V2(@RequestParam("q") String query) {
        List<Book> books = bookRepository.searchBooks(query);
        return Books.of(books);
    }

    record Books(List<Book> books){
        public static Books of(List<Book> books) {
            return new Books(books);
        }
    }
}
