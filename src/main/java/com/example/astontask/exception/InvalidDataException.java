package com.example.astontask.exception;

/**
 * Exception thrown when invalid data is provided in the application.
 * Extends RuntimeException, allowing it to be thrown during normal application flow without requiring explicit handling.
 */
public class InvalidDataException extends RuntimeException {

    /**
     * Constructs a new InvalidDataException with the specified detail message.
     *
     * @param message the detail message, which will be saved for later retrieval by the {@link Throwable#getMessage()} method
     */
    public InvalidDataException(String message) {
        super(message);
    }

}
