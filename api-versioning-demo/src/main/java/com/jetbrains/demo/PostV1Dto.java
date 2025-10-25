package com.jetbrains.demo;

public record PostV1Dto(
        Long id,
        String title,
        String body,
        Long userId) {
}