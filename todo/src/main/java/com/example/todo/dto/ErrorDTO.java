package com.example.todo.dto;

/**
 * A class dto that represents an error message to the front end.
 * This class is only design for handling validation failures on requested data
 *
 * @param statusCode The status code
 * @param fieldName The field name that the error occurred in
 * @param errorMessage The error message
 */
public record ErrorDTO (Integer statusCode, String fieldName, String errorMessage) { }
