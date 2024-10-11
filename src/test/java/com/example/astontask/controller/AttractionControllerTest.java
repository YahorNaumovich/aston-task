package com.example.astontask.controller;

import com.example.astontask.dto.request.AttractionCreateDTO;
import com.example.astontask.dto.response.AttractionDTO;
import com.example.astontask.model.type.AttractionType;
import com.example.astontask.service.AttractionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class AttractionControllerTest {

    @Mock
    private AttractionService attractionService;

    @InjectMocks
    private AttractionController attractionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return 201 when attraction is successfully created")
    void testAddAttraction() {

        AttractionCreateDTO attractionCreateDTO = new AttractionCreateDTO();
        doNothing().when(attractionService).addAttraction(any(AttractionCreateDTO.class));

        ResponseEntity<Void> response = attractionController.addAttraction(attractionCreateDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    @DisplayName("Should return 200 and a list of attractions when getAllAttractions is called")
    void testGetAllAttractions() {

        AttractionDTO attractionDTO = new AttractionDTO();
        when(attractionService.getAllAttractions(anyString(), any(AttractionType.class)))
                .thenReturn(List.of(attractionDTO));

        ResponseEntity<List<AttractionDTO>> response = attractionController.getAllAttractions("name", AttractionType.PALACE);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());

    }

    @Test
    @DisplayName("Should return 200 and a list of attractions when getAllAttractionsByLocality is called")
    void testGetAllAttractionsByLocality() {

        AttractionDTO attractionDTO = new AttractionDTO();
        when(attractionService.getAllAttractionsByLocality(anyLong()))
                .thenReturn(List.of(attractionDTO));

        ResponseEntity<List<AttractionDTO>> response = attractionController.getAllAttractionsByLocality(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());

    }

    @Test
    @DisplayName("Should return 200 when attraction description is updated successfully")
    void testUpdateAttractionDescription() {

        doNothing().when(attractionService).updateAttractionDescription(anyLong(), anyString());

        ResponseEntity<Void> response = attractionController.updateAttractionDescription(1L, "New attraction description");

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    @DisplayName("Should return 200 when attraction is successfully deleted")
    void testDeleteAttraction() {

        doNothing().when(attractionService).deleteAttraction(anyLong());

        ResponseEntity<Void> response = attractionController.deleteAttraction(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

}
