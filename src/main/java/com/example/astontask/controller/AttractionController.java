package com.example.astontask.controller;

import com.example.astontask.dto.request.AttractionCreateDTO;
import com.example.astontask.dto.response.AttractionDTO;
import com.example.astontask.model.type.AttractionType;
import com.example.astontask.service.AttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing attractions.
 * Provides endpoints for adding, retrieving, updating, and deleting attractions.
 */
@RestController
@RequestMapping("/api/attractions")
@RequiredArgsConstructor
@Tag(name = "Attractions", description = "Methods for working with attractions")
public class AttractionController {

    private final AttractionService attractionService;

    /**
     * Adds a new attraction and its assistances.
     *
     * @param attractionDTO the attraction data to be added
     * @return ResponseEntity with status 201 (Created) if the attraction was successfully created,
     *         or an appropriate error status otherwise
     */
    @PostMapping
    @Operation(summary = "Add attraction", description = "Adds new attraction and its assistances")
    public ResponseEntity<Void> addAttraction(@RequestBody AttractionCreateDTO attractionCreateDTO) {
        attractionService.addAttraction(attractionCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all attractions with optional sorting and filtering.
     *
     * @param sortBy the field by which the results should be sorted
     * @param type   the type of attractions to filter by
     * @return ResponseEntity containing the list of attractions, or an appropriate error status
     */
    @GetMapping
    @Operation(summary = "Get all attractions", description = "Gets a list of all attractions with the ability to filter and sort")
    public ResponseEntity<List<AttractionDTO>> getAllAttractions(@RequestParam String sortBy,
                                                                 @RequestParam AttractionType type) {
        return new ResponseEntity<>(attractionService.getAllAttractions(sortBy, type), HttpStatus.OK);
    }

    /**
     * Retrieves a list of attractions based on the locality ID.
     *
     * @param localityId the ID of the locality to filter attractions by
     * @return ResponseEntity containing the list of attractions, or an appropriate error status
     */
    @GetMapping("/locality/{localityId}")
    @Operation(summary = "Get all attractions by locality", description = "Gets a list of all attractions by specified locality id")
    public ResponseEntity<List<AttractionDTO>> getAllAttractionsByLocality(@PathVariable Long localityId) {
        return new ResponseEntity<>(attractionService.getAllAttractionsByLocality(localityId), HttpStatus.OK);
    }

    /**
     * Updates the description of an existing attraction.
     *
     * @param id          the ID of the attraction to update
     * @param description the new description for the attraction
     * @return ResponseEntity with status 200 (OK) if the update was successful,
     *         or an appropriate error status otherwise
     */
    @PutMapping("/{id}")
    @Operation(summary = "Change attraction description", description = "Changes attraction description")
    public ResponseEntity<Void> updateAttractionDescription(@PathVariable Long id, @RequestBody String description) {
        attractionService.updateAttractionDescription(id, description);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Deletes an attraction by its ID.
     *
     * @param id the ID of the attraction to delete
     * @return ResponseEntity with status 200 (OK) if the deletion was successful,
     *         or an appropriate error status otherwise
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete attraction", description = "Deletes an attraction by id")
    public ResponseEntity<Void> deleteAttraction(@PathVariable Long id) {
        attractionService.deleteAttraction(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
