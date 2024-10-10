package com.example.astontask.service;

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
import com.example.astontask.service.impl.AttractionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AttractionServiceTest {

    @Mock
    private AttractionRepository attractionRepository;

    @Mock
    private AssistanceRepository assistanceRepository;

    @Mock
    private LocalityRepository localityRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AttractionServiceImpl attractionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should successfully add new attraction")
    void shouldAddAttractionSuccessfully() {

        AttractionCreateDTO attractionCreateDTO = new AttractionCreateDTO();
        Attraction attraction = new Attraction();

        when(modelMapper.map(attractionCreateDTO, Attraction.class)).thenReturn(attraction);
        when(attractionRepository.save(attraction)).thenReturn(attraction);

        attractionService.addAttraction(attractionCreateDTO);

        verify(attractionRepository, times(1)).save(attraction);
        verify(assistanceRepository, times(1)).saveAll(attraction.getAssistance());

    }

    @Test
    @DisplayName("Should throw InvalidDataException when adding attraction fails")
    void shouldThrowInvalidDataExceptionWhenAddAttractionFails() {

        AttractionCreateDTO attractionCreateDTO = new AttractionCreateDTO();

        when(modelMapper.map(attractionCreateDTO, Attraction.class)).thenThrow(RuntimeException.class);

        assertThrows(InvalidDataException.class, () -> attractionService.addAttraction(attractionCreateDTO));
        verify(attractionRepository, never()).save(any());

    }

    @Test
    @DisplayName("Should retrieve all attractions by locality ID")
    void shouldRetrieveAllAttractionsByLocalityId() {

        Long localityId = 1L;
        Locality locality = new Locality();
        Attraction attraction = new Attraction();
        AttractionDTO attractionDTO = new AttractionDTO();

        when(localityRepository.findById(localityId)).thenReturn(Optional.of(locality));
        when(attractionRepository.findByLocality(locality)).thenReturn(List.of(attraction));
        when(modelMapper.map(attraction, AttractionDTO.class)).thenReturn(attractionDTO);

        List<AttractionDTO> result = attractionService.getAllAttractionsByLocality(localityId);

        assertEquals(1, result.size());
        verify(localityRepository, times(1)).findById(localityId);
        verify(attractionRepository, times(1)).findByLocality(locality);

    }

    @Test
    @DisplayName("Should throw EntityNotFoundException if locality is not found")
    void shouldThrowEntityNotFoundExceptionWhenLocalityNotFound() {

        Long localityId = 1L;

        when(localityRepository.findById(localityId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> attractionService.getAllAttractionsByLocality(localityId));

    }

    @Test
    @DisplayName("Should update attraction description successfully")
    void shouldUpdateAttractionDescriptionSuccessfully() {

        Long attractionId = 1L;
        String newDescription = "New Description";
        Attraction attraction = new Attraction();

        when(attractionRepository.findById(attractionId)).thenReturn(Optional.of(attraction));

        attractionService.updateAttractionDescription(attractionId, newDescription);

        assertEquals(newDescription, attraction.getDescription());
        verify(attractionRepository, times(1)).save(attraction);

    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when updating non-existing attraction")
    void shouldThrowEntityNotFoundExceptionWhenUpdatingNonExistingAttraction() {

        Long attractionId = 1L;
        String newDescription = "New Description";

        when(attractionRepository.findById(attractionId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> attractionService.updateAttractionDescription(attractionId, newDescription));

    }

    @Test
    @DisplayName("Should delete attraction by ID")
    void shouldDeleteAttractionSuccessfully() {

        Long attractionId = 1L;
        Attraction attraction = new Attraction();

        when(attractionRepository.findById(attractionId)).thenReturn(Optional.of(attraction));

        attractionService.deleteAttraction(attractionId);

        verify(attractionRepository, times(1)).delete(attraction);

    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when deleting non-existing attraction")
    void shouldThrowEntityNotFoundExceptionWhenDeletingNonExistingAttraction() {

        Long attractionId = 1L;

        when(attractionRepository.findById(attractionId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> attractionService.deleteAttraction(attractionId));

    }

    @Test
    @DisplayName("Should retrieve all attractions with sorting and filtering")
    void shouldRetrieveAllAttractionsWithSortingAndFiltering() {

        String sortBy = "name";
        AttractionType type = AttractionType.PARK;
        Attraction attraction = new Attraction();
        AttractionDTO attractionDTO = new AttractionDTO();
        Sort sort = Sort.by(Sort.Order.asc(sortBy));

        when(attractionRepository.findByType(type, sort)).thenReturn(List.of(attraction));
        when(modelMapper.map(attraction, AttractionDTO.class)).thenReturn(attractionDTO);

        List<AttractionDTO> result = attractionService.getAllAttractions(sortBy, type);

        assertEquals(1, result.size());
        verify(attractionRepository, times(1)).findByType(type, sort);

    }

}
