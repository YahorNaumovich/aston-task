package com.example.astontask.dto;

import com.example.astontask.model.type.AssistanceType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssistanceDTO {
    private Long id;
    private AssistanceType type;
    private String description;
    private String provider;
}

