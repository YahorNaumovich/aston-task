package com.example.astontask.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidDataExceptionTest {

    @Test
    @DisplayName("Should set the correct message when InvalidDataException is thrown")
    void whenInvalidDataExceptionIsThrown_thenCorrectMessageIsSet() {

        String expectedMessage = "Invalid data provided";

        InvalidDataException exception = new InvalidDataException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage(), "Exception message should match the expected message");

    }

}
