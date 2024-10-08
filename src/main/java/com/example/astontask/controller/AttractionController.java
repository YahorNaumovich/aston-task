package com.example.astontask.controller;

import com.example.astontask.model.Attraction;
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
    @Operation(summary = "Add attraction", description = "Adds new attraction and its assistances.")
    public void addAttraction(@RequestBody Attraction attraction) {
        attractionService.addAttraction(attraction);
    }

    @GetMapping("/locality/{localityId}")
    @Operation(summary = "Get all attractions by locality", description = "Gets all atractions by specified locality id")
    public List<Attraction> getAllAttractionsByLocality(@PathVariable Long localityId) {
        return attractionService.getAllAttractionsByLocality(localityId);
    }
}
