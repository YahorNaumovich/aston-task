package com.example.astontask.dto;

import com.example.astontask.model.type.AttractionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Data Transfer Object (DTO) for Attraction.
 * Represents the details of an attraction, including its name, creation date, description, type, locality, and related assistances.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDTO {

    private Long id;

    private String name;

    private Date creationDate;

    private String description;

    private AttractionType type;

    private LocalityDTO locality;

    private List<AssistanceDTO> assistance;

}
