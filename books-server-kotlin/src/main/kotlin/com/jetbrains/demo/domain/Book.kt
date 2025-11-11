package com.jetbrains.demo.domain

import java.time.Instant

data class Book(
    var id: Long?,
    var isbn: String,
    var title: String,
    var author: String,
    var createdAt: Instant,
    var updatedAt: Instant?,
    var version: Int
)