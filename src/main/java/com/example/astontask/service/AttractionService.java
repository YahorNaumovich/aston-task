package com.example.astontask.service;

import com.example.astontask.model.Attraction;
import com.example.astontask.model.type.AttractionType;

import java.util.List;

public interface AttractionService {
    void addAttraction(Attraction attraction);

    List<Attraction> getAllAttractionsByLocality(Long localityId);

    void updateAttractionDescription(Long id, String description);

    void deleteAttraction(Long id);

    List<Attraction> getAllAttractions(String sortBy, AttractionType type);
}
