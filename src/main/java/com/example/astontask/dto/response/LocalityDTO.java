package com.example.astontask.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for Locality.
 * Represents the details of a locality, including its name, region, and related attractions.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalityDTO {

    private Long id;

    private String name;

    private String region;

}
