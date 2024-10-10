package com.example.astontask.controller;

import com.example.astontask.exception.EntityNotFoundException;
import com.example.astontask.exception.InvalidDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling exceptions across the whole application.
 * It handles specific exceptions like EntityNotFoundException and InvalidDataException,
 * as well as generic exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles EntityNotFoundException.
     *
     * @param ex the EntityNotFoundException thrown
     * @return a ResponseEntity with status 404 (Not Found) and the exception message
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles InvalidDataException.
     *
     * @param ex the InvalidDataException thrown
     * @return a ResponseEntity with status 400 (Bad Request) and the exception message
     */
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> handleInvalidDataException(InvalidDataException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all other generic exceptions.
     *
     * @param ex the generic exception thrown
     * @return a ResponseEntity with status 500 (Internal Server Error) and a generic error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
