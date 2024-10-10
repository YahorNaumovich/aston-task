package com.example.astontask.service;

import com.example.astontask.dto.request.LocalityCreateDTO;

/**
 * Service interface for managing localities.
 * Provides methods for adding localities.
 */
public interface LocalityService {

    /**
     * Adds a new locality.
     *
     * @param localityDTO the data transfer object containing details of the locality to be added
     */
    void addLocality(LocalityCreateDTO localityCreateDTO);

}
