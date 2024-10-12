package com.example.astontask.controller;

import com.example.astontask.dto.request.LocalityCreateDTO;
import com.example.astontask.service.LocalityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing localities.
 * Provides endpoints for adding and managing localities.
 */
@RestController
@RequestMapping("/api/localities")
@RequiredArgsConstructor
@Tag(name = "Localities", description = "Methods for working with localities")
public class LocalityController {

    private final LocalityService localityService;

    /**
     * Adds a new locality.
     *
     * @param localityCreateDTO the data of the locality to be added
     * @return ResponseEntity with status 201 (Created) if the locality was successfully created,
     * or an appropriate error status otherwise
     */
    @PostMapping
    @Operation(summary = "Add locality", description = "Adds new locality")
    @ApiResponse(responseCode = "201", description = "Locality successfully created")
    public ResponseEntity<Void> addLocality(@RequestBody LocalityCreateDTO localityCreateDTO) {
        localityService.addLocality(localityCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
