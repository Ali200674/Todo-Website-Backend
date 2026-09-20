package com.example.todo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

/**
 * A class that handles all the exceptions thrown in the application
 *
 * @author Ali Izoyev
 * @version 1.0x
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler{

    /**
     * Method that handles all the NoSuchElementExceptions
     *
     * @param e The exception thrown
     * @return A ResponseEntity detailing what happened
     */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoSuchException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    /**
     * Method that handles all MethodArgumentNotValidException errors
     *
     * @return A ResponseEntity detailing what happened
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMethodArgumentNotValidException() {
        return ResponseEntity.badRequest().body("One or more values are blank!");
    }
}
