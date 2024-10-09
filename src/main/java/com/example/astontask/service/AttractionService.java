package com.example.astontask.service;

import com.example.astontask.dto.AttractionDTO;
import com.example.astontask.model.type.AttractionType;

import java.util.List;

public interface AttractionService {

    void addAttraction(AttractionDTO attractionDTO);

    List<AttractionDTO> getAllAttractionsByLocality(Long localityId);

    void updateAttractionDescription(Long id, String description);

    void deleteAttraction(Long id);

    List<AttractionDTO> getAllAttractions(String sortBy, AttractionType type);

}
