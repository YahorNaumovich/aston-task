package com.example.astontask.service.impl;

import com.example.astontask.dto.request.LocalityCreateDTO;
import com.example.astontask.dto.response.LocalityDTO;
import com.example.astontask.exception.InvalidDataException;
import com.example.astontask.model.Locality;
import com.example.astontask.repository.LocalityRepository;
import com.example.astontask.service.LocalityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link LocalityService} interface for managing localities.
 * This service handles the logic for adding new localities.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class LocalityServiceImpl implements LocalityService {

    private final LocalityRepository localityRepository;
    private final ModelMapper modelMapper;

    /**
     * Adds a new locality to the repository.
     * The method maps the provided {@link LocalityDTO} to a {@link Locality} entity and saves it.
     *
     * @param localityCreateDTO the data transfer object containing the locality details to be added
     * @throws InvalidDataException if there is an error during the locality creation process
     */
    @Override
    public void addLocality(LocalityCreateDTO localityCreateDTO) {

        try {

            Locality locality = modelMapper.map(localityCreateDTO, Locality.class);
            localityRepository.save(locality);

        } catch (Exception e) {

            throw new InvalidDataException("Invalid data provided for locality creation");

        }

    }
}
