package com.example.astontask.controller;

import com.example.astontask.model.Attraction;
import com.example.astontask.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attractions")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @PostMapping
    public void addAttraction(@RequestBody Attraction attraction) {
        attractionService.addAttraction(attraction);
    }
}
