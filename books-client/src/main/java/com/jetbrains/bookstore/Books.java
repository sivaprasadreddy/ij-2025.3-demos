package com.jetbrains.bookstore;

import java.util.List;

record Books(List<Book> books){
}

record Book(
        Long id,
        String isbn,
        String title,
        String author) {
}