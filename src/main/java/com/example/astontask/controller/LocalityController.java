package com.example.astontask.controller;

import com.example.astontask.dto.LocalityDTO;
import com.example.astontask.service.LocalityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/localities")
@RequiredArgsConstructor
@Tag(name = "Localities", description = "Methods for working with localities")
public class LocalityController {

    private final LocalityService localityService;

    @PostMapping
    @Operation(summary = "Add locality", description = "Adds new locality")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Locality successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input, object invalid"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> addLocality(@RequestBody LocalityDTO localityDTO) {
        localityService.addLocality(localityDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //TODO - Изменение данных по местоположению (возможно изменение только полей: краткое описание, сопровождение (услуга)).
}
