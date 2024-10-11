package com.example.astontask.controller;

import com.example.astontask.dto.request.AttractionCreateDTO;
import com.example.astontask.dto.response.AttractionDTO;
import com.example.astontask.model.type.AttractionType;
import com.example.astontask.service.AttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
     * @param attractionCreateDTO the attraction data to be added
     * @return ResponseEntity with status 201 (Created) if the attraction was successfully created,
     * or an appropriate error status otherwise
     */
    @PostMapping
    @Operation(summary = "Add attraction", description = "Adds new attraction and its assistances")
    @ApiResponse(responseCode = "201", description = "Attraction successfully created")
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
    @ApiResponse(responseCode = "200", description = "List of attractions retrieved")
    public ResponseEntity<List<AttractionDTO>> getAllAttractions(@RequestParam @Schema(description = "Field by which the attractions should be sorted. Possible values: 'id', 'name', 'type', 'creationDate'", example = "name") String sortBy,
                                                                 @RequestParam @Schema(description = "Type of attraction to filter by", example = "PALACE") AttractionType type) {
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
    @ApiResponse(responseCode = "200", description = "List of attractions retrieved")
    public ResponseEntity<List<AttractionDTO>> getAllAttractionsByLocality(@PathVariable @Schema(description = "Id of the locality used to search for attractions") Long localityId) {
        return new ResponseEntity<>(attractionService.getAllAttractionsByLocality(localityId), HttpStatus.OK);
    }

    /**
     * Updates the description of an existing attraction.
     *
     * @param id          the ID of the attraction to update
     * @param description the new description for the attraction
     * @return ResponseEntity with status 200 (OK) if the update was successful,
     * or an appropriate error status otherwise
     */
    @PutMapping("/{id}")
    @Operation(summary = "Change attraction description", description = "Changes attraction description")
    @ApiResponse(responseCode = "200", description = "Attraction description updated successfully")
    public ResponseEntity<Void> updateAttractionDescription(@PathVariable @Schema(description = "Id of the attraction to be updated") Long id,
                                                            @RequestBody @Schema(example = "New attraction description") String description) {
        attractionService.updateAttractionDescription(id, description);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Deletes an attraction by its ID.
     *
     * @param id the ID of the attraction to delete
     * @return ResponseEntity with status 200 (OK) if the deletion was successful,
     * or an appropriate error status otherwise
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete attraction", description = "Deletes an attraction by id")
    @ApiResponse(responseCode = "200", description = "Attraction deleted successfully")
    public ResponseEntity<Void> deleteAttraction(@PathVariable @Schema(description = "Id of the attraction to be deleted") Long id) {
        attractionService.deleteAttraction(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
