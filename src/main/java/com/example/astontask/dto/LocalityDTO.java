package com.example.astontask.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalityDTO {

    private Long id;

    private String name;

    private String region;

    @JsonIgnore
    private List<AttractionDTO> attractions;

}
