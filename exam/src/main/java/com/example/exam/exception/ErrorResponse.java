package com.example.exam.exception;

public record ErrorResponse(
        String code,
        String message
) {
}
