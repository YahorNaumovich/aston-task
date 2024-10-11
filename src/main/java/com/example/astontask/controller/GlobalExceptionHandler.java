package com.example.astontask.controller;

import com.example.astontask.dto.response.ErrorResponseDTO;
import com.example.astontask.exception.EntityNotFoundException;
import com.example.astontask.exception.InvalidDataException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
     * @return a ResponseEntity with status 404 (Not Found) and an error message
     */
    @ExceptionHandler(EntityNotFoundException.class)

    @ApiResponse(responseCode = "404", description = "Entity not found",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles InvalidDataException.
     *
     * @param ex the InvalidDataException thrown
     * @return a ResponseEntity with status 400 (Bad Request) and an error message
     */
    @ExceptionHandler(InvalidDataException.class)
    @ApiResponse(responseCode = "400", description = "Invalid data provided",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    public ResponseEntity<ErrorResponseDTO> handleInvalidDataException(InvalidDataException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all other generic exceptions.
     *
     * @param ex the generic exception thrown
     * @return a ResponseEntity with status 500 (Internal Server Error) and a generic error message
     */
    @ExceptionHandler(Exception.class)
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO("An unexpected error occurred: " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
