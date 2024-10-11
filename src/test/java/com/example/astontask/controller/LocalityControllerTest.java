package com.example.astontask.controller;

import com.example.astontask.dto.request.LocalityCreateDTO;
import com.example.astontask.service.LocalityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LocalityControllerTest {

    @Mock
    private LocalityService localityService;

    @InjectMocks
    private LocalityController localityController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return 201 when locality is successfully created")
    void testAddLocality() {

        LocalityCreateDTO localityCreateDTO = new LocalityCreateDTO();
        doNothing().when(localityService).addLocality(any(LocalityCreateDTO.class));

        ResponseEntity<Void> response = localityController.addLocality(localityCreateDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

}
