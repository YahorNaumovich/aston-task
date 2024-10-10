package com.example.astontask.dto.response;

import com.example.astontask.model.type.AssistanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}

