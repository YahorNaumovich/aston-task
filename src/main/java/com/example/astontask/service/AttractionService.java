package com.example.astontask.service;

import com.example.astontask.dto.request.AttractionCreateDTO;
import com.example.astontask.dto.response.AttractionDTO;
import com.example.astontask.model.type.AttractionType;

import java.util.List;

/**
 * Service interface for managing attractions.
 * Provides methods for adding, updating, retrieving, and deleting attractions.
 */
public interface AttractionService {

    /**
     * Adds a new attraction.
     *
     * @param attractionDTO the data transfer object containing the details of the attraction to be added
     */
    void addAttraction(AttractionCreateDTO attractionCreateDTO);

    /**
     * Retrieves all attractions by locality ID.
     *
     * @param localityId the ID of the locality for which attractions are to be retrieved
     * @return a list of {@link AttractionDTO} representing all attractions in the specified locality
     */
    List<AttractionDTO> getAllAttractionsByLocality(Long localityId);

    /**
     * Updates the description of an existing attraction.
     *
     * @param id the ID of the attraction to update
     * @param description the new description of the attraction
     */
    void updateAttractionDescription(Long id, String description);

    /**
     * Deletes an attraction by its ID.
     *
     * @param id the ID of the attraction to be deleted
     */
    void deleteAttraction(Long id);

    /**
     * Retrieves all attractions with sorting and filtering options.
     *
     * @param sortBy the field by which to sort the attractions
     * @param type the type of attractions to filter by
     * @return a list of {@link AttractionDTO} representing all attractions sorted and filtered by the specified criteria
     */
    List<AttractionDTO> getAllAttractions(String sortBy, AttractionType type);

}
