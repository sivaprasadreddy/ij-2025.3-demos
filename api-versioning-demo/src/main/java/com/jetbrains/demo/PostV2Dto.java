package com.jetbrains.demo;

public record PostV2Dto(
        Long id,
        String title,
        String body,
        PostStatus status,
        Long userId) {
}