package com.jetbrains.demo

import com.jetbrains.demo.domain.Book
import com.jetbrains.demo.domain.BookRepository
import org.springframework.beans.factory.BeanRegistrarDsl
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.servlet.function.bodyWithType
import org.springframework.web.servlet.function.router

class BeansRegistrar: BeanRegistrarDsl({
    registerBean<BookRepository>()
    registerBean {
        mainRouter(bean<BookRepository>())
    }
})

fun mainRouter(
    bookRepository: BookRepository
) = router {
    GET("/api/books") {
        bookRepository.findAll().let {books ->
            ok().contentType(APPLICATION_JSON).bodyWithType(Books(books))
        }
    }

    GET("/api/books/search") {
        val query : String = it.params()["q"]?.firstOrNull() ?: ""
        bookRepository.searchBooks(query).let { books ->
            ok().contentType(APPLICATION_JSON).bodyWithType(Books(books))
        }
    }

    GET("/api/books/{isbn}") { request ->
        val isbn = request.pathVariable("isbn")
        bookRepository.findByIsbn(isbn).let { book ->
            ok().contentType(APPLICATION_JSON).bodyWithType(book)
        }?: notFound().build()
    }

}

data class Books(val books: List<Book>)