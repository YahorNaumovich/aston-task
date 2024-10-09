package com.example.astontask.dto;

import com.example.astontask.model.Attraction;
import com.example.astontask.model.type.AssistanceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object (DTO) for Assistance.
 * Represents the details of an assistance, including its type, description, provider, and related attractions.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssistanceDTO {

    private Long id;

    private AssistanceType type;

    private String description;

    private String provider;

    @JsonIgnore
    private List<Attraction> attractions;

}

