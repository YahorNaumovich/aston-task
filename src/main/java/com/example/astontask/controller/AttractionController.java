package com.example.astontask.controller;

import com.example.astontask.dto.AttractionDTO;
import com.example.astontask.model.type.AttractionType;
import com.example.astontask.service.AttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    public void addAttraction(@RequestBody AttractionDTO attractionDTO) {
        attractionService.addAttraction(attractionDTO);
    }

    @GetMapping
    @Operation(summary = "Get all attractions", description = "Gets a list of all attractions with the ability to filter and sort")
    public List<AttractionDTO> getAllAttractions(@RequestParam String sortBy,
                                              @RequestParam AttractionType type){
        return attractionService.getAllAttractions(sortBy, type);
    }

    @GetMapping("/locality/{localityId}")
    @Operation(summary = "Get all attractions by locality", description = "Gets a list of all attractions by specified locality id")
    public List<AttractionDTO> getAllAttractionsByLocality(@PathVariable Long localityId) {
        return attractionService.getAllAttractionsByLocality(localityId);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Change attraction description", description = "Changes attraction description")
    public void updateAttractionDescription(@PathVariable Long id, @RequestBody String description) {
        attractionService.updateAttractionDescription(id, description);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete attraction", description = "Deletes an attraction by id")
    public void deleteAttraction(@PathVariable Long id) {
        attractionService.deleteAttraction(id);
    }
}
