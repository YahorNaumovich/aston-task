package com.example.astontask.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalityDTO {
    private Long id;
    private String name;
    private String region;
    private List<AttractionDTO> attractions;
}
