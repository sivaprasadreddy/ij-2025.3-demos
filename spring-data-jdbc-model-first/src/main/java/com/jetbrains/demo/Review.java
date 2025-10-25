package com.jetbrains.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table(name = "reviews")
public class Review {
    @Id
    private Long id;

    @Column("book_id")
    private AggregateReference<Book, Long> book;

    @Column("user_id")
    private AggregateReference<User, Long> user;

    private Integer rating;

    private String review;

    private Instant createdAt;

    private Instant updatedAt;

    @Version
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AggregateReference<Book, Long> getBook() {
        return book;
    }

    public void setBook(AggregateReference<Book, Long> book) {
        this.book = book;
    }

    public AggregateReference<User, Long> getUser() {
        return user;
    }

    public void setUser(AggregateReference<User, Long> user) {
        this.user = user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}