package com.example.astontask.service.impl;

import com.example.astontask.dto.request.AttractionCreateDTO;
import com.example.astontask.dto.response.AttractionDTO;
import com.example.astontask.exception.EntityNotFoundException;
import com.example.astontask.exception.InvalidDataException;
import com.example.astontask.model.Attraction;
import com.example.astontask.model.Locality;
import com.example.astontask.model.type.AttractionType;
import com.example.astontask.repository.AssistanceRepository;
import com.example.astontask.repository.AttractionRepository;
import com.example.astontask.repository.LocalityRepository;
import com.example.astontask.service.AttractionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing attractions.
 * Implements the methods for adding, updating, retrieving, and deleting attractions.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository attractionRepository;
    private final AssistanceRepository assistanceRepository;
    private final LocalityRepository localityRepository;
    private final ModelMapper modelMapper;

    /**
     * Adds a new attraction.
     *
     * @param attractionDTO the data transfer object containing the details of the attraction to be added
     * @throws InvalidDataException if the provided data is invalid
     */
    @Override
    public void addAttraction(AttractionCreateDTO attractionCreateDTO) {

        try {

            Attraction attraction = modelMapper.map(attractionCreateDTO, Attraction.class);
            attractionRepository.save(attraction);
            assistanceRepository.saveAll(attraction.getAssistance());

        } catch (Exception e) {

            throw new InvalidDataException("Invalid data provided for attraction creation");

        }
    }

    /**
     * Retrieves all attractions by locality ID.
     *
     * @param localityId the ID of the locality for which attractions are to be retrieved
     * @return a list of {@link AttractionDTO} representing all attractions in the specified locality
     * @throws EntityNotFoundException if no locality is found with the specified ID
     */
    @Override
    public List<AttractionDTO> getAllAttractionsByLocality(Long localityId) {

        Locality locality = localityRepository
                .findById(localityId)
                .orElseThrow(() -> new EntityNotFoundException("Locality with ID " + localityId + " not found"));

        List<Attraction> attractions = attractionRepository.findByLocality(locality);

        return attractions
                .stream()
                .map(attraction -> modelMapper.map(attraction, AttractionDTO.class))
                .toList();

    }

    /**
     * Updates the description of an existing attraction.
     *
     * @param id          the ID of the attraction to update
     * @param description the new description of the attraction
     * @throws EntityNotFoundException if the attraction with the specified ID is not found
     */
    @Override
    public void updateAttractionDescription(Long id, String description) {

        Attraction attractionToUpdate = attractionRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attraction with ID " + id + " not found"));

        attractionToUpdate.setDescription(description);
        attractionRepository.save(attractionToUpdate);

    }

    /**
     * Deletes an attraction by its ID.
     *
     * @param id the ID of the attraction to be deleted
     * @throws EntityNotFoundException if the attraction with the specified ID is not found
     */
    @Override
    public void deleteAttraction(Long id) {

        Attraction attractionToDelete = attractionRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attraction with ID " + id + " not found"));

        attractionRepository.delete(attractionToDelete);

    }

    /**
     * Retrieves all attractions with sorting and filtering options.
     *
     * @param sortBy the field by which to sort the attractions
     * @param type   the type of attractions to filter by
     * @return a list of {@link AttractionDTO} representing all attractions sorted and filtered by the specified criteria
     */
    @Override
    public List<AttractionDTO> getAllAttractions(String sortBy, AttractionType type) {

        Sort sort = Sort.by(Sort.Order.asc(sortBy));
        List<Attraction> attractions = attractionRepository.findByType(type, sort);

        return attractions.stream().map(attraction -> modelMapper.map(attraction, AttractionDTO.class)).toList();

    }
}
