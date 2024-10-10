package com.example.astontask.service;

import com.example.astontask.dto.LocalityDTO;
import com.example.astontask.exception.InvalidDataException;
import com.example.astontask.model.Locality;
import com.example.astontask.repository.LocalityRepository;
import com.example.astontask.service.impl.LocalityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class LocalityServiceTest {


    @Mock
    private LocalityRepository localityRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private LocalityServiceImpl localityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should successfully add a new locality when valid data is provided")
    void shouldAddNewLocalityWhenValidDataProvided() {

        LocalityDTO localityDTO = new LocalityDTO();
        localityDTO.setName("Test Locality");
        localityDTO.setRegion("Test Region");

        Locality mappedLocality = new Locality();
        mappedLocality.setName("Test Locality");
        mappedLocality.setRegion("Test Region");

        when(modelMapper.map(localityDTO, Locality.class)).thenReturn(mappedLocality);
        when(localityRepository.save(mappedLocality)).thenReturn(mappedLocality);

        localityService.addLocality(localityDTO);

        verify(localityRepository, times(1)).save(mappedLocality);

    }

    @Test
    @DisplayName("Should throw InvalidDataException when error occurs during locality creation")
    void shouldThrowInvalidDataExceptionWhenErrorOccursDuringCreation() {

        LocalityDTO localityDTO = new LocalityDTO();
        localityDTO.setName("Invalid Locality");

        when(modelMapper.map(localityDTO, Locality.class)).thenThrow(new RuntimeException("Mapping failed"));

        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> localityService.addLocality(localityDTO));

        assertEquals("Invalid data provided for locality creation", exception.getMessage());
        verify(localityRepository, never()).save(any(Locality.class));

    }

}
