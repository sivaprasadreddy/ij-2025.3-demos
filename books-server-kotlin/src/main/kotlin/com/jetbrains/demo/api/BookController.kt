/*
package com.jetbrains.demo.api

import com.jetbrains.demo.domain.Book
import com.jetbrains.demo.domain.BookRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
class BookController(private val bookRepository: BookRepository) {

    @GetMapping
    fun getAll(): Books {
        val books = bookRepository.findAll()
        return Books(books)
    }

    @GetMapping("/{isbn}")
    fun getByIsbn(@PathVariable isbn: String): ResponseEntity<Book> {
        return bookRepository.findByIsbn(isbn)
            .map{book -> ResponseEntity.ok(book)}
            .orElse(ResponseEntity.notFound().build())
    }

    @GetMapping(value = ["/search"], version = "1.0")
    fun searchBooks(@RequestParam("q") query: String): Books {
        val books = bookRepository.findByTitleContainingIgnoreCase(query)
        return Books(books)
    }

    @GetMapping(value = ["/search"], version = "2.0")
    fun searchBooksV2(@RequestParam("q") query: String): Books {
        val books = bookRepository.searchBooks("%$query%")
        return Books(books)
    }

    data class Books(val books: List<Book>)

}
*/
