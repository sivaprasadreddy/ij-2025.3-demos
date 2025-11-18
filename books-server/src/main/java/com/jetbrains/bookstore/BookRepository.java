package com.jetbrains.bookstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByTitleContainingIgnoreCase(String query);

    @Query("SELECT b FROM Book b WHERE b.isbn LIKE %?1% OR b.title LIKE %?1% OR b.author LIKE %?1%")
    List<Book> searchBooks(String query);
}
