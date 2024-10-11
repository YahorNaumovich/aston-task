package com.example.astontask.controller;

import com.example.astontask.dto.response.ErrorResponseDTO;
import com.example.astontask.exception.EntityNotFoundException;
import com.example.astontask.exception.InvalidDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return 404 and error message when EntityNotFoundException is thrown")
    void testHandleEntityNotFoundException() {

        EntityNotFoundException ex = new EntityNotFoundException("Entity not found");

        ResponseEntity<ErrorResponseDTO> response = globalExceptionHandler.handleEntityNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Entity not found", response.getBody().getErrorMessage());

    }

    @Test
    @DisplayName("Should return 400 and error message when InvalidDataException is thrown")
    void testHandleInvalidDataException() {

        InvalidDataException ex = new InvalidDataException("Invalid data provided");

        ResponseEntity<ErrorResponseDTO> response = globalExceptionHandler.handleInvalidDataException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Invalid data provided", response.getBody().getErrorMessage());

    }

    @Test
    @DisplayName("Should return 500 and error message when generic exception is thrown")
    void testHandleGenericException() {

        Exception ex = new Exception("Generic error");

        ResponseEntity<ErrorResponseDTO> response = globalExceptionHandler.handleGenericException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("An unexpected error occurred: Generic error", response.getBody().getErrorMessage());

    }

}
