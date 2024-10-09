package com.example.astontask.controller;

import com.example.astontask.dto.AttractionDTO;
import com.example.astontask.model.type.AttractionType;
import com.example.astontask.service.AttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attractions")
@RequiredArgsConstructor
@Tag(name = "Attractions", description = "Methods for working with attractions")
public class AttractionController {

    private final AttractionService attractionService;

    @PostMapping
    @Operation(summary = "Add attraction", description = "Adds new attraction and its assistances")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Attraction successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input, object invalid"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> addAttraction(@RequestBody AttractionDTO attractionDTO) {
        attractionService.addAttraction(attractionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all attractions", description = "Gets a list of all attractions with the ability to filter and sort")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "400", description = "Invalid sort or filter criteria"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<AttractionDTO>> getAllAttractions(@RequestParam String sortBy,
                                                                 @RequestParam AttractionType type) {
        return new ResponseEntity<>(attractionService.getAllAttractions(sortBy, type), HttpStatus.OK);
    }

    @GetMapping("/locality/{localityId}")
    @Operation(summary = "Get all attractions by locality", description = "Gets a list of all attractions by specified locality id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "404", description = "Locality not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<AttractionDTO>> getAllAttractionsByLocality(@PathVariable Long localityId) {
        return new ResponseEntity<>(attractionService.getAllAttractionsByLocality(localityId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Change attraction description", description = "Changes attraction description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Description successfully updated"),
            @ApiResponse(responseCode = "404", description = "Attraction not found"),
            @ApiResponse(responseCode = "400", description = "Invalid description input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> updateAttractionDescription(@PathVariable Long id, @RequestBody String description) {
        attractionService.updateAttractionDescription(id, description);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete attraction", description = "Deletes an attraction by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Attraction successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Attraction not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteAttraction(@PathVariable Long id) {
        attractionService.deleteAttraction(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
