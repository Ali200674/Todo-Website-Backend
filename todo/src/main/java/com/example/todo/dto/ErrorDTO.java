package com.example.todo.dto;

public record ErrorDTO (Integer statusCode, String fieldName, String errorMessage) { }
