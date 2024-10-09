package com.example.astontask.dto;

import com.example.astontask.model.type.AttractionType;
import lombok.*;
import java.util.Date;
import java.util.List;

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
