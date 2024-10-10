package com.example.astontask.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityNotFoundExceptionTest {

    @Test
    @DisplayName("Should set the correct message when EntityNotFoundException is thrown")
    void whenEntityNotFoundExceptionIsThrown_thenCorrectMessageIsSet() {

        String expectedMessage = "Entity not found in the database";

        EntityNotFoundException exception = new EntityNotFoundException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage(), "Exception message should match the expected message");

    }

}
