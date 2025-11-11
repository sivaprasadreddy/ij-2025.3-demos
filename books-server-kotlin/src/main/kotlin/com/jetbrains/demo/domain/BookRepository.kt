package com.jetbrains.demo.domain

import org.springframework.jdbc.core.simple.JdbcClient
import java.sql.ResultSet
import java.util.*

class BookRepository(private val jdbcClient: JdbcClient) {

    fun findAll(): List<Book> {
        return jdbcClient.sql("""SELECT * FROM books ORDER BY id desc""")
            .query(BookRowMapper)
            .list()

    }

    fun findByIsbn(isbn: String): Optional<Book> {
        return jdbcClient.sql("""SELECT * FROM books WHERE isbn = :isbn""")
                    .param("isbn", isbn)
                    .query(BookRowMapper)
                    .optional()
    }

    fun findByTitleContainingIgnoreCase(query: String): List<Book> {
        return jdbcClient.sql("""SELECT * FROM books WHERE lower(title) ILIKE :query""")
                    .param("query", "%${query.lowercase()}%")
                    .query(BookRowMapper)
                    .list()

    }

    fun searchBooks(query: String): List<Book> {
        return jdbcClient.sql("""
                        SELECT * FROM books 
                        WHERE lower(isbn) ILIKE :query OR lower(title) ILIKE :query OR lower(author) ILIKE :query
                    """.trimIndent())
                    .param("query", "%${query.lowercase()}%")
                    .query(BookRowMapper)
                    .list()
    }

    object BookRowMapper : org.springframework.jdbc.core.RowMapper<Book> {
        override fun mapRow(rs: ResultSet, rowNum: Int): Book {
            return Book(
                   id = rs.getLong("id"),
                   title = rs.getString("title"),
                   isbn = rs.getString("isbn"),
                   author = rs.getString("author"),
                   createdAt = rs.getTimestamp("created_at").toInstant(),
                   updatedAt = rs.getTimestamp("updated_at")?.toInstant(),
                   version = rs.getInt("version")
               )
        }

    }
}
