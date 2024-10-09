package com.example.astontask.dto;

import com.example.astontask.model.Attraction;
import com.example.astontask.model.type.AssistanceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

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

