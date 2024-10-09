package com.example.astontask.exception;

/**
 * Exception thrown when an entity is not found in the database or other data source.
 * Extends RuntimeException, allowing it to be thrown during the normal flow of the application
 * without requiring explicit handling.
 */
public class EntityNotFoundException extends RuntimeException {

    /**
     * Constructs a new EntityNotFoundException with the specified detail message.
     *
     * @param message the detail message, which will be saved for later retrieval by the {@link Throwable#getMessage()} method
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

}