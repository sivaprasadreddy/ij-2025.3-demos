package com.jetbrains.myapp;

public record Post(
        Long id,
        String title,
        String body,
        Long userId) {
}
